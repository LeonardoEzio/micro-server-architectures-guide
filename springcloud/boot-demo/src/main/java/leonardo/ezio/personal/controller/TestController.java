package leonardo.ezio.personal.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author : LeonardoEzio
 * @Date: 2021-08-19 18:43
 */
@RestController
public class TestController {

    @RequestMapping("test")
    public String test(){
        return "success !!!";
    }
}
