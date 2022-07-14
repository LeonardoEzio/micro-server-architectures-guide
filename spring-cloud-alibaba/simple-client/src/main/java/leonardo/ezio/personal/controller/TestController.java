package leonardo.ezio.personal.controller;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @Author : LeonardoEzio
 * @Date: 2021-06-15 16:02
 */
@RestController
@RequestMapping("consumer")
public class TestController {

    @Resource
    private RestTemplate restTemplate;

    @RequestMapping("test/balance")
    @LoadBalanced
    public String testLoadBalance(){
        String result = restTemplate.getForObject("http://simple-server/test/loadBalance", String.class);
        return   "consumer-service获得数据:" + result;
    }
}
