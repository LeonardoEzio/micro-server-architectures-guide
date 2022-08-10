package leonardo.ezio.personal.config;

import com.netflix.hystrix.strategy.HystrixPlugins;

import javax.annotation.PostConstruct;

/**
 * @Description : 熔断线程池配置
 * @Author : LeonardoEzio
 * @Date: 2021-10-11 11:37
 */
//@Configuration
public class HystrixThreadConfig {

    @PostConstruct
    public void init(){
        HystrixPlugins.reset();
        HystrixPlugins.getInstance().registerConcurrencyStrategy(new HystrixThreadStrategy());
    }
}
