package leonardo.ezio.personal.channel;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * @Description : 自定义通道（输出通道）
 * @Author : LeonardoEzio
 * @Date: 2022-01-27 11:38
 */
public interface CustomSource {

    /**
     * 主题 alarmTp的输出通道
     * */
    String ALARM_OUTPUT = "alarmOutput";

    /**
     * 主题 billTp的输出通道
     * */
    String BILL_OUTPUT = "billOutput";

    @Output(ALARM_OUTPUT)
    MessageChannel alarmOutput();

    @Output(BILL_OUTPUT)
    MessageChannel billOutput();
}
