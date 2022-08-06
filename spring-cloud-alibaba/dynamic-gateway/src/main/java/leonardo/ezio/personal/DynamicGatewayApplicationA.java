package leonardo.ezio.personal;

import leonardo.ezio.personal.config.GrayLoadBalancerConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClients;

/**
 * @Description : 动态网关
 * @Author : LeonardoEzio
 * @Date: 2022-07-25 11:09
 */
/**
 *  * 指定所使用的负载均衡策略，defaultConfiguration代表对所有服务生效
 *  * @LoadBalancerClients(defaultConfiguration = GrayLoadBalancerConfig.class)
 *  * 指定某个服务所使用的负载均衡策略
 *  * @LoadBalancerClient(name = ,configuration = )
 * */
@EnableDiscoveryClient
@SpringBootApplication
@LoadBalancerClients(defaultConfiguration = GrayLoadBalancerConfig.class)
public class DynamicGatewayApplicationA {

    public static void main(String[] args) {
        SpringApplication.run(DynamicGatewayApplicationA.class);
    }

}
