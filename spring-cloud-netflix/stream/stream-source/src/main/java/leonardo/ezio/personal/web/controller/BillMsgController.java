package leonardo.ezio.personal.web.controller;

import leonardo.ezio.personal.service.BillMsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description :
 * @Author : LeonardoEzio
 * @Date: 2022-01-27 11:58
 */
@RestController
@RequestMapping("billMsg")
public class BillMsgController {

    @Autowired
    private BillMsgService billMsgService;

    @GetMapping("send/{msg}")
    public String send(@PathVariable String msg){
        billMsgService.send(msg);
        return "success";
    }
}
