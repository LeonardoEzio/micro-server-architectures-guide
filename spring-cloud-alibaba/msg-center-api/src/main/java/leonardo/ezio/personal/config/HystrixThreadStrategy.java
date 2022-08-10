package leonardo.ezio.personal.config;

import com.netflix.hystrix.strategy.concurrency.HystrixConcurrencyStrategy;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.concurrent.Callable;

/**
 * @Description : 自定义Hystrix线程池策略
 * @Author : LeonardoEzio
 * @Date: 2021-10-11 11:38
 */
public class HystrixThreadStrategy extends HystrixConcurrencyStrategy {

    @Override
    public <T> Callable<T> wrapCallable(Callable<T> callable) {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return new Callable<T>() {
            @Override
            public T call() throws Exception {
                try {
                    if (null != servletRequestAttributes) {
                        RequestContextHolder.setRequestAttributes(servletRequestAttributes);
                    }
                    return callable.call();
                } finally {
                    RequestContextHolder.resetRequestAttributes();
                }
            }
        };
    }
}
