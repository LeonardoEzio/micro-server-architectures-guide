package leonardo.ezio.personal.listener;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ReflectUtil;
import com.alibaba.cloud.stream.binder.rocketmq.consuming.RocketMQListenerBindingContainer;
import com.alibaba.cloud.stream.binder.rocketmq.integration.RocketMQInboundChannelAdapter;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.impl.consumer.ConsumeMessageConcurrentlyService;
import org.apache.rocketmq.client.impl.consumer.DefaultMQPushConsumerImpl;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.cloud.stream.binder.Binding;
import org.springframework.cloud.stream.binder.DefaultBinding;
import org.springframework.cloud.stream.binding.InputBindingLifecycle;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Optional;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Description : 获取sink组件 底层inputBinding线程池
 * @Author : LeonardoEzio
 * @Date: 2022-10-08 18:29
 */
@Component
public class SinkApplicationListener implements ApplicationListener<ApplicationStartedEvent> {

    @Override
    public void onApplicationEvent(ApplicationStartedEvent applicationStartedEvent) {
        ConfigurableApplicationContext applicationContext = applicationStartedEvent.getApplicationContext();
        InputBindingLifecycle bindingLifecycle = applicationContext.getBean(InputBindingLifecycle.class);
        Collection<Binding<Object>> inputBindings = Optional.ofNullable(ReflectUtil.getFieldValue(bindingLifecycle, "inputBindings")).map(each -> (Collection<Binding<Object>>) each).orElse(null);
        if (CollectionUtil.isEmpty(inputBindings)) {
            System.out.println("inputBidings is empty");
        }
        try {
            for (Binding<Object> each : inputBindings) {
                String bindingName = each.getName();
                DefaultBinding defaultBinding = (DefaultBinding) each;
                RocketMQInboundChannelAdapter lifecycle = (RocketMQInboundChannelAdapter) ReflectUtil.getFieldValue(defaultBinding, "lifecycle");
                RocketMQListenerBindingContainer rocketMQListenerContainer = (RocketMQListenerBindingContainer) ReflectUtil.getFieldValue(lifecycle, "rocketMQListenerContainer");
                DefaultMQPushConsumer consumer = rocketMQListenerContainer.getConsumer();
                DefaultMQPushConsumerImpl defaultMQPushConsumerImpl = consumer.getDefaultMQPushConsumerImpl();
                ConsumeMessageConcurrentlyService consumeMessageService = (ConsumeMessageConcurrentlyService) defaultMQPushConsumerImpl.getConsumeMessageService();
                ThreadPoolExecutor consumeExecutor = (ThreadPoolExecutor) ReflectUtil.getFieldValue(consumeMessageService, "consumeExecutor");

            }
        } catch (Exception ex) {

        }
    }
}
