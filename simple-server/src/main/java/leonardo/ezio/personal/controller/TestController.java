package leonardo.ezio.personal.controller;


import leonardo.ezio.personal.stater.wrapper.MyJsonWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author : LeonardoEzio
 * @Date: 2021-06-02 17:23
 */
@RestController
@RequestMapping("test")
public class TestController {

    @Autowired(required = false)
    private MyJsonWrapper jsonWrapper;
    /**
    * @description
    *
    * @name : testOne
    * @param :
    * @return : * @return {@link String }
    * @date : 2021/6/2 17:35
    *
    **/

    @Value("${server.port}")
    private String port;

    @GetMapping("/one")
    public String testOne(){
        if (jsonWrapper != null){
            return jsonWrapper.wrapper("test success by nacos !!");
        }else {
            return "test success by nacos !!";
        }
    }

    @GetMapping("loadBalance")
    public String loadBalance(){
        return "response from " + port;
    }

}
