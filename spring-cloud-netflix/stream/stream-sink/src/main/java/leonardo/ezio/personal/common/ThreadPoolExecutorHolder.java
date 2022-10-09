package leonardo.ezio.personal.common;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Description :
 * @Author : LeonardoEzio
 * @Date: 2022-10-09 11:24
 */
public class ThreadPoolExecutorHolder {

    private static final Map<String, ThreadPoolExecutor> THREAD_POOL_EXECUTOR_MAP = new HashMap<>();

    public static void put(String key,ThreadPoolExecutor executor){
        THREAD_POOL_EXECUTOR_MAP.put(key, executor);
    }

    public static ThreadPoolExecutor get(String key){
        return THREAD_POOL_EXECUTOR_MAP.get(key);
    }
}
