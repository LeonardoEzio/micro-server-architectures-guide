package leonardo.ezio.personal.factory;

import feign.hystrix.FallbackFactory;
import leonardo.ezio.personal.api.MsgCenterServer;
import leonardo.ezio.personal.fallback.MsgCenterServerFallBack;
import org.springframework.stereotype.Component;

/**
 * @Description : 熔断回调工厂
 * @Author : LeonardoEzio
 * @Date: 2022-08-09 18:29
 */
@Component
public class MsgCenterServerFallBackFactory implements FallbackFactory<MsgCenterServer> {

    @Override
    public MsgCenterServer create(Throwable throwable) {
        MsgCenterServerFallBack msgCenterServerFallBack = new MsgCenterServerFallBack();
        msgCenterServerFallBack.setCause(throwable);
        return msgCenterServerFallBack;
    }
}
