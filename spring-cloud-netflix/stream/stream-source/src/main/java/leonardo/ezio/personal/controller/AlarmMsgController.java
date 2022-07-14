package leonardo.ezio.personal.controller;

import leonardo.ezio.personal.service.AlarmMsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description :
 * @Author : LeonardoEzio
 * @Date: 2022-01-27 11:57
 */
@RestController
@RequestMapping("alarmMsg")
public class AlarmMsgController {

    @Autowired
    private AlarmMsgService alarmMsgService;

    @GetMapping("send/{msg}")
    public String send(@PathVariable String msg){
        alarmMsgService.send(msg);
        return "success";
    }
}
