package leonardo.ezio.personal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Description :
 * @Author : LeonardoEzio
 * @Date: 2022-07-14 16:02
 */
@Controller
public class DemoController {

    @RequestMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("hello")
    public String hello(){
        return "Hello Spring Security";
    }
}
