package cn.xtool.util;

import org.lionsoul.ip2region.xdb.Searcher;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class IPutil {
    private static final String dxpath = "E:\\sources\\mine\\github\\XCommonTool\\src\\main\\resources\\ip2region.xdb";
    private static final byte[] vIndex;

    static {
        try {
            vIndex = Searcher.loadContentFromFile(dxpath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws IOException {
        String ip = "1.1.1.0";
        System.out.println("getFromXDB(ip) = " + getFromXDBFile(ip));
        System.out.println("getFromVector(ip) = " + getFromCache(ip));
    }

    public static String getFromCache(String ipAddress) throws IOException {
        Searcher searcher;
        try {
            searcher = Searcher.newWithBuffer(vIndex);
        } catch (Exception e) {
            System.out.printf("failed to create vectorIndex cached searcher with `%s`: %s\n", dxpath, e);
            return null;
        }

        // 3、查询
        try {
            long sTime = System.nanoTime();
            String region = searcher.search(ipAddress);
            long cost = TimeUnit.NANOSECONDS.toMicros(System.nanoTime() - sTime);
            System.out.printf("{region: %s, ioCount: %d, took: %d μs}\n", region, searcher.getIOCount(), cost);
            return region;
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 4、关闭资源
        searcher.close();
        return null;
        // 备注：每个线程需要单独创建一个独立的 Searcher 对象，但是都共享全局的制度 vIndex 缓存。
    }

    public static String getFromXDBFile(String ipAddress) {
        // 1、创建 searcher 对象
        String dbPath = "E:\\sources\\mine\\github\\XCommonTool\\src\\main\\resources\\ip2region.xdb";
        Searcher searcher;
        try {
            searcher = Searcher.newWithFileOnly(dbPath);
        } catch (IOException e) {
            System.out.printf("failed to create searcher with `%s`: %s\n", dbPath, e);
            return null;
        }

        // 2、查询
        try {
            long sTime = System.nanoTime();
            String region = searcher.search(ipAddress);
            long cost = TimeUnit.NANOSECONDS.toMicros(System.nanoTime() - sTime);
            System.out.printf("{region: %s, ioCount: %d, took: %d μs}\n", region, searcher.getIOCount(), cost);
            return region;
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 3、关闭资源
        try {
            searcher.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // 备注：并发使用，每个线程需要创建一个独立的 searcher 对象单独使用。
        return null;
    }
}
