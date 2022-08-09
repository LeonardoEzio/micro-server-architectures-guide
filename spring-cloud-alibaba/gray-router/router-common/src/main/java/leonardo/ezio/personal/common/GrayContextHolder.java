package leonardo.ezio.personal.common;

/**
 * @Description : 灰度版本上下文
 * @Author : LeonardoEzio
 * @Date: 2022-08-09 15:37
 */
public class GrayContextHolder {

    private static final ThreadLocal<String> GRAY_CONTEXT = new ThreadLocal<>();

    public static void putContext(String version){
        GRAY_CONTEXT.set(version);
    }

    public static String getContext(){
        return GRAY_CONTEXT.get();
    }

    public static void removeContext(){
        GRAY_CONTEXT.remove();
    }
}
