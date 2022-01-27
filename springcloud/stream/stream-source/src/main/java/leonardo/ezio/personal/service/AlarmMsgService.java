package leonardo.ezio.personal.service;

import leonardo.ezio.personal.channel.CustomSource;
import leonardo.ezio.personal.msg.AlarmMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

/**
 * @Description : 提醒消息发送服务
 * @Author : LeonardoEzio
 * @Date: 2022-01-27 11:53
 */
@Service
@EnableBinding(CustomSource.class)
public class AlarmMsgService {

    private static final Logger log = LoggerFactory.getLogger(AlarmMsgService.class);

    private CustomSource source;

    @Autowired
    public AlarmMsgService(CustomSource source){
        this.source = source;
    }

    public void send(String msg){
        AlarmMsg alarmMsg = new AlarmMsg();
        alarmMsg.setBody(msg);
        source.alarmOutput().send(MessageBuilder.withPayload(alarmMsg).build());
        log.info("alarmMsg send success !!! ");
    }
}
