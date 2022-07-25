package leonardo.ezio.personal.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @ResponseBody
    @RequestMapping("hello")
    public String hello(){
        return "Hello Spring Security";
    }
}
