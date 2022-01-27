package leonardo.ezio.personal.listener;

import leonardo.ezio.personal.channel.CustomSink;
import leonardo.ezio.personal.msg.AlarmMsg;
import leonardo.ezio.personal.msg.BillMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

/**
 * @Description : 自定义消息监听
 * @Author : LeonardoEzio
 * @Date: 2022-01-27 14:07
 */
@EnableBinding(CustomSink.class)
public class CustomMsgListener {

    private static final Logger log = LoggerFactory.getLogger(CustomMsgListener.class);

    @StreamListener(CustomSink.ALARM_INPUT)
    public void handlerAlarmMsg(AlarmMsg msg){
        log.info("receive msg : {}", msg);
    }

    @StreamListener(CustomSink.BILL_INPUT)
    public void handlerBillMsg(BillMsg msg){
        log.info("receive msg : {}", msg);
    }
}
