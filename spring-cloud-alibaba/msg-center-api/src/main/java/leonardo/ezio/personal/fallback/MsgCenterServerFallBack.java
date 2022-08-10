package leonardo.ezio.personal.fallback;

import leonardo.ezio.personal.api.MsgCenterServer;
import leonardo.ezio.personal.dto.MsgDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @Description : 消息中心熔断处理
 * @Author : LeonardoEzio
 * @Date: 2022-08-09 18:29
 */
@Component
public class MsgCenterServerFallBack implements MsgCenterServer {

    private static final Logger log = LoggerFactory.getLogger(MsgCenterServerFallBack.class);

    private Throwable cause;

    public Throwable getCause() {
        return cause;
    }

    public void setCause(Throwable cause) {
        this.cause = cause;
    }

    @Override
    public String sendUserRegisterMsg(MsgDto msgDto) {
        if (null != cause){
            log.error("send user register message failed !!! request info : {} ; exception info : {}",msgDto,cause.getMessage());
        }else {
            log.error("send user register message failed !!! request info : {} ",msgDto);
        }
        return "msgCenterFallBack";
    }
}
