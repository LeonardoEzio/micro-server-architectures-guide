package leonardo.ezio.personal.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description :
 * @Author : LeonardoEzio
 * @Date: 2022-07-20 10:34
 */
@RestController
public class IndexController {

    @GetMapping("/")
    public String def(){
        return "Welcome Spring Security";
    }

    @RequestMapping("index")
    public String index(){
        return "Spring Security with no frontend";
    }

    @RequestMapping("errorPage")
    public String error(){
        return "login failed";
    }

}
