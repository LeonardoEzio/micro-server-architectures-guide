package leonardo.ezio.personal.web.controller;

import leonardo.ezio.personal.api.MsgCenterServer;
import leonardo.ezio.personal.dto.MsgDto;
import leonardo.ezio.personal.web.dto.UserRegisterDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description : 用户注册控制器
 * @Author : LeonardoEzio
 * @Date: 2022-07-25 12:16
 */
@RestController
@RequestMapping("register")
public class RegisterController {

    @Value("${server.port}")
    private String serverPort;

    @Autowired
    private MsgCenterServer msgCenterServer;

    @PostMapping("singIn")
    public String singIn(@RequestBody UserRegisterDto registerDto){
        MsgDto msgDto = new MsgDto();
        String msgResult = msgCenterServer.sendUserRegisterMsg(msgDto);
        return "user register success "+ serverPort +  "; "+msgResult;
    }
}
