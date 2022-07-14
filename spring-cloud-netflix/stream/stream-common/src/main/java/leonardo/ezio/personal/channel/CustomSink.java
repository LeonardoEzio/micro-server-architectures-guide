package leonardo.ezio.personal.channel;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 * @Description : 自定义消息通道(输入通道)
 * @Author : LeonardoEzio
 * @Date: 2022-01-27 11:40
 */
public interface CustomSink {

    /**
     * 主题 alarmTp的输出通道
     * */
    String ALARM_INPUT = "alarmInput";

    /**
     * 主题 billTp的输出通道
     * */
    String BILL_INPUT = "billInput";

    @Input(ALARM_INPUT)
    SubscribableChannel alarmInput();

    @Input(BILL_INPUT)
    SubscribableChannel billInput();

}
