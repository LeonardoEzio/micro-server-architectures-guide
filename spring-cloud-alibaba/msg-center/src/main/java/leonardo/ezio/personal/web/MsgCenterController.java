package leonardo.ezio.personal.web;

import leonardo.ezio.personal.dto.MsgDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description :
 * @Author : LeonardoEzio
 * @Date: 2022-08-09 18:35
 */
@RestController
public class MsgCenterController {

    @Value("${server.port}")
    private String serverPort;

    @PostMapping("testSend")
    public String testSend(){
        return "msg test send success "+ serverPort;
    }

    @PostMapping("sendUserRegisterMsg")
    public String sendUserRegisterMsg(@RequestBody MsgDto MsgDto){

        return "user register message send success "+ serverPort;
    }
}
