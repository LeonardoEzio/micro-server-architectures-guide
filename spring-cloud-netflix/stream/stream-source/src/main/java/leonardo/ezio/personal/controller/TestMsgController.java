package leonardo.ezio.personal.controller;

import leonardo.ezio.personal.service.TestMsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description :
 * @Author : LeonardoEzio
 * @Date: 2022-01-26 15:26
 */
@RestController
@RequestMapping("testMsg")
public class TestMsgController {

    @Autowired
    private TestMsgService testMsgService;

    @GetMapping("send/{msg}")
    public String send(@PathVariable String msg){
        testMsgService.send(msg);
        return "success";
    }
}
