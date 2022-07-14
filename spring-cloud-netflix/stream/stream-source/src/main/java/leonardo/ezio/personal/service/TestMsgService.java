package leonardo.ezio.personal.service;

import leonardo.ezio.personal.msg.TestMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;


/**
 * @Description : 测试消息发生服务类
 * @Author : LeonardoEzio
 * @Date: 2022-01-26 14:53
 */
@Service
@EnableBinding(Source.class)
public class TestMsgService {

    private static final Logger log = LoggerFactory.getLogger(TestMsgService.class);

    private Source source;

    @Autowired
    public TestMsgService(Source source){
        this.source = source;
    }

    public void send(String msg){
        TestMsg testMsg = new TestMsg();
        testMsg.setBody(msg);
        source.output().send(MessageBuilder.withPayload(testMsg).build());
        log.info("testMsg send success !!! ");
    }
}
