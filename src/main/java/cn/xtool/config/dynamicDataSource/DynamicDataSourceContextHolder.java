package cn.xtool.config.dynamicDataSource;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * （切换数据源必须在调用service之前进行，也就是开启事务之前）
 * 动态数据源上下文
 * @author guomh
 * @date 2019/11/06
 */
public class DynamicDataSourceContextHolder {
    private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>() {
        /**
         * 将 master 数据源的 key作为默认数据源的 key
         */
        @Override
        protected String initialValue() {
            return "master";
        }
    };
    /**
     * 数据源的 key集合，用于切换时判断数据源是否存在
     */
    public static List<Object> dataSourceKeys = new ArrayList<>();
    /**
     * 切换数据源
     * @param key
     */
    public static void setDataSourceKey(String key) {
        contextHolder.set(key);
    }
    /**
     * 获取数据源
     * @return
     */
    public static String getDataSourceKey() {
        return contextHolder.get();
    }
    /**
     * 重置数据源
     */
    public static void clearDataSourceKey() {
        contextHolder.remove();
    }
    /**
     * 判断是否包含数据源
     * @param key 数据源key
     * @return
     */
    public static boolean containDataSourceKey(String key) {
        return dataSourceKeys.contains(key);
    }
    /**
     * 添加数据源keys
     * @param keys
     * @return
     */
    @SuppressWarnings("UnusedReturnValue")
    public static boolean addDataSourceKeys(Collection<?> keys) {
        return dataSourceKeys.addAll(keys);
    }
}