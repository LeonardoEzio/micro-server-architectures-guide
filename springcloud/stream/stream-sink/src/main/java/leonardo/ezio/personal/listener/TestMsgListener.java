package leonardo.ezio.personal.listener;

import leonardo.ezio.personal.msg.TestMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

/**
 * @Description : 消息监听者
 * @Author : LeonardoEzio
 * @Date: 2022-01-26 15:31
 */
@EnableBinding(Sink.class)
public class TestMsgListener {

    private static final Logger log = LoggerFactory.getLogger(TestMsgListener.class);

    @StreamListener(Sink.INPUT)
    public void handlerMsg(TestMsg testMsg){
        log.info("receive msg : {}", testMsg);
    }
}
