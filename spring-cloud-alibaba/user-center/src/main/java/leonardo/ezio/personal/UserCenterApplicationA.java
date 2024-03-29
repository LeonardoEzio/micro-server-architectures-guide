package leonardo.ezio.personal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Description : 用户中心启动类
 * @Author : LeonardoEzio
 * @Date: 2022-07-25 11:09
 */
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
@ServletComponentScan
public class UserCenterApplicationA {

    public static void main(String[] args) {
        SpringApplication.run(UserCenterApplicationA.class, args);
    }

}
