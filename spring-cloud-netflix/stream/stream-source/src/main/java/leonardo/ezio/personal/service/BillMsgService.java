package leonardo.ezio.personal.service;

import leonardo.ezio.personal.channel.CustomSource;
import leonardo.ezio.personal.msg.BillMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

/**
 * @Description : 账单消息发送服务
 * @Author : LeonardoEzio
 * @Date: 2022-01-27 11:55
 */
@Service
@EnableBinding(CustomSource.class)
public class BillMsgService {

    private static final Logger log = LoggerFactory.getLogger(BillMsgService.class);

    private CustomSource source;

    @Autowired
    public BillMsgService(CustomSource source){
        this.source = source;
    }

    public void send(String msg){
        BillMsg billMsg = new BillMsg();
        billMsg.setBody(msg);
        source.billOutput().send(MessageBuilder.withPayload(billMsg).build());
        log.info("billMsg send success !!! ");
    }
}
