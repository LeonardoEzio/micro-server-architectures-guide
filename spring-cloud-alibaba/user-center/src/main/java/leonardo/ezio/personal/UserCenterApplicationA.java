package leonardo.ezio.personal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Description : 用户中心启动类
 * @Author : LeonardoEzio
 * @Date: 2022-07-25 11:09
 */
@EnableDiscoveryClient
@SpringBootApplication
public class UserCenterApplicationA {

    public static void main(String[] args) {
        SpringApplication.run(UserCenterApplicationA.class, args);
    }

}
