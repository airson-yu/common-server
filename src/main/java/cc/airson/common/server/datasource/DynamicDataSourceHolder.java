package cc.airson.common.server.datasource;

/**
 * 使用ThreadLocal技术来记录当前线程中的数据源的key
 *
 * @author airson http://airson.cc
 * @since 1.0 2020年6月9日17:22:19
 */
public class DynamicDataSourceHolder {

    //写库对应的数据源key
    public static final String MASTER = "master";

    //读库对应的数据源key
    public static final String SLAVE = "slave";

    //使用ThreadLocal记录当前线程的数据源key
    private static final ThreadLocal<String> holder = new ThreadLocal<>();

    /**
     * 设置数据源key
     *
     * @param key
     */
    public static void putDataSourceKey(String key) {
        holder.set(key);
    }

    /**
     * 获取数据源key
     *
     * @return
     */
    public static String getDataSourceKey() {
        return holder.get();
    }

    /**
     * 标记Master（一般为写库）
     */
    public static void markMaster() {
        putDataSourceKey(MASTER);
    }

    /**
     * 标记Slave（一般为读库）
     */
    public static void markSlave() {
        putDataSourceKey(SLAVE);
    }

    /**
     * 判断是否为Master
     */
    public static boolean isMaster() {
        return MASTER.equals(holder.get());
    }

}
