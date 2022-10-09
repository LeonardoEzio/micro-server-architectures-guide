package leonardo.ezio.personal.web.controller;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ReflectUtil;
import com.alibaba.cloud.stream.binder.rocketmq.consuming.RocketMQListenerBindingContainer;
import com.alibaba.cloud.stream.binder.rocketmq.integration.RocketMQInboundChannelAdapter;
import leonardo.ezio.personal.common.ApplicationContextHolder;
import leonardo.ezio.personal.common.ThreadPoolExecutorHolder;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.impl.consumer.ConsumeMessageConcurrentlyService;
import org.apache.rocketmq.client.impl.consumer.DefaultMQPushConsumerImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.binder.Binding;
import org.springframework.cloud.stream.binder.DefaultBinding;
import org.springframework.cloud.stream.binding.InputBindingLifecycle;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Description :
 * @Author : LeonardoEzio
 * @Date: 2022-10-09 11:29
 */
@RestController
@RequestMapping("executorMonitor")
public class SinkExecutorMonitorController {

    private Logger log = LoggerFactory.getLogger(SinkExecutorMonitorController.class);

    @PostMapping("update/{key}")
    public String updateExecutor(@PathVariable String key){
        ThreadPoolExecutor threadPoolExecutor = ThreadPoolExecutorHolder.get(key);
        log.info("Thread Pool Executor Config Before Update !! core size : {} , max size : {}",threadPoolExecutor.getCorePoolSize(),threadPoolExecutor.getMaximumPoolSize());
        threadPoolExecutor.setCorePoolSize(Runtime.getRuntime().availableProcessors());
        threadPoolExecutor.setMaximumPoolSize(Runtime.getRuntime().availableProcessors()*2);
        log.info("Thread Pool Executor Config After Update !! core size : {} , max size : {}",threadPoolExecutor.getCorePoolSize(),threadPoolExecutor.getMaximumPoolSize());
        return "update success";
    }

    @GetMapping("check")
    public String checkExecutor(){
        InputBindingLifecycle bindingLifecycle = ApplicationContextHolder.getBean(InputBindingLifecycle.class);
        Collection<Binding<Object>> inputBindings = Optional.ofNullable(ReflectUtil.getFieldValue(bindingLifecycle, "inputBindings")).map(each -> (Collection<Binding<Object>>) each).orElse(null);
        if (CollectionUtil.isEmpty(inputBindings)) {
            System.out.println("inputBidings is empty");
        }
        try {
            for (Binding<Object> each : inputBindings) {
                DefaultBinding defaultBinding = (DefaultBinding) each;
                RocketMQInboundChannelAdapter lifecycle = (RocketMQInboundChannelAdapter) ReflectUtil.getFieldValue(defaultBinding, "lifecycle");
                RocketMQListenerBindingContainer rocketMQListenerContainer = (RocketMQListenerBindingContainer) ReflectUtil.getFieldValue(lifecycle, "rocketMQListenerContainer");
                DefaultMQPushConsumer consumer = rocketMQListenerContainer.getConsumer();
                DefaultMQPushConsumerImpl defaultMQPushConsumerImpl = consumer.getDefaultMQPushConsumerImpl();
                ConsumeMessageConcurrentlyService consumeMessageService = (ConsumeMessageConcurrentlyService) defaultMQPushConsumerImpl.getConsumeMessageService();
                ThreadPoolExecutor consumeExecutor = (ThreadPoolExecutor) ReflectUtil.getFieldValue(consumeMessageService, "consumeExecutor");
                log.info("binding : {} thread pool config !!! core size : {} ; max size : {} ",each.getName(),consumeExecutor.getCorePoolSize(),consumeExecutor.getMaximumPoolSize());
            }
        } catch (Exception ex) {

        }
        return "success";
    }
}
