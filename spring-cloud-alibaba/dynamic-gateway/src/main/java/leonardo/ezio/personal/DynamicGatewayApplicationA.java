package leonardo.ezio.personal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Description : 动态网关
 * @Author : LeonardoEzio
 * @Date: 2022-07-25 11:09
 */
@EnableDiscoveryClient
@SpringBootApplication
public class DynamicGatewayApplicationA {

    public static void main(String[] args) {
        SpringApplication.run(DynamicGatewayApplicationA.class);
    }

}
