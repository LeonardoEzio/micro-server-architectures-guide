package leonardo.ezio.personal.web.controller;

import leonardo.ezio.personal.stater.wrapper.MyJsonWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description : 自定义starter测试
 * @Author : LeonardoEzio
 * @Date: 2022-04-15 12:09
 */
@RestController
@RequestMapping("starter")
public class MyStarterController {

    @Autowired
    private MyJsonWrapper myJsonWrapper;

    @RequestMapping("test")
    public String test(){
        return myJsonWrapper.getPrefix() + " success !!!" + myJsonWrapper.getSuffix();
    }
}
