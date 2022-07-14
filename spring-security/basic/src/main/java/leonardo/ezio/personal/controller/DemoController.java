package leonardo.ezio.personal.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description :
 * @Author : LeonardoEzio
 * @Date: 2022-07-14 16:02
 */
@RestController
public class DemoController {

    @GetMapping("hello")
    public String hello(){
        return "Hello Spring Security";
    }
}
