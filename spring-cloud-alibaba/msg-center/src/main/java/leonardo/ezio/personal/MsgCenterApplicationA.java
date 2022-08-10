package leonardo.ezio.personal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Description :
 * @Author : LeonardoEzio
 * @Date: 2022-08-09 17:28
 */
@EnableDiscoveryClient
@SpringBootApplication
public class MsgCenterApplicationA {

    public static void main(String[] args) {
        SpringApplication.run(MsgCenterApplicationA.class, args);
    }
}
