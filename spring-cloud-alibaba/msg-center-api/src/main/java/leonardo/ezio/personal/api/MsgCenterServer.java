package leonardo.ezio.personal.api;

import leonardo.ezio.personal.config.OpenFeignRequestInterceptor;
import leonardo.ezio.personal.dto.MsgDto;
import leonardo.ezio.personal.factory.MsgCenterServerFallBackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @Description :
 * @Author : LeonardoEzio
 * @Date: 2022-08-09 17:38
 */
@FeignClient(value = "msg-center-a",path = "/msg",fallbackFactory = MsgCenterServerFallBackFactory.class, configuration = OpenFeignRequestInterceptor.class)
public interface MsgCenterServer {

    /**
    * 发送用户注册消息
    *
    * @name sendUserRegisterMsg
    * @param msgDto 消息实体
    * @return  * @return {@link String }
    * @date  2022/8/9 17:41
    **/
    @PostMapping("sendUserRegisterMsg")
    String sendUserRegisterMsg(@RequestBody MsgDto msgDto);
}
