package cn.xtool.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.List;

/**
 * @author xwxu
 * @description
 * @date 2022-07-28
 */
public class EasyExcelUtil {
    public static void main(String[] args) throws IOException {
        String path = "D:\\Desktop\\企业碳排放+.xlsx";
        List<List<Object>> rows = getRows(Files.newInputStream(new File(path).toPath()), 1, 1, 1, 1, false, false);
    }

    /***
     * @description: 指定行列范围强制读取Excel单元格
     *
     * @param: inputStream 输入文件流
     * @param: startRow 开始行 (0行开始
     * @param: endRow   结束行(0行结束
     * @param: startCell    开始列(0列开始
     * @param: endCell  结束列(0列结束
     * @param: enforce  强制读取单元格( 空返回null
     * @param: readHeader  是否读取表头
     **/
    public static List<List<Object>> getRows(InputStream inputStream, int startRow, int endRow, int startCell, int endCell, boolean enforce, boolean readHeader) {
        return null;
    }
}
