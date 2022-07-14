package leonardo.ezio.personal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Description : SpringCloud Stream消息生产者应用
 * @Author : LeonardoEzio
 * @Date: 2022-01-26 14:44
 */
@SpringBootApplication
public class CloudStreamSourceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudStreamSourceApplication.class, args);
    }
}
