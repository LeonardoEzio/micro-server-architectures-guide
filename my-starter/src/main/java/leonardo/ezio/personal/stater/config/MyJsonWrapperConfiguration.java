package leonardo.ezio.personal.stater.config;

import leonardo.ezio.personal.stater.wrapper.MyJsonWrapper;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;

/**
 * @Author : LeonardoEzio
 * @Date: 2021-06-11 15:08
 *
 * 在 /resources/META-INF下中的spring.factories中指定了此配置类
 * 则可以省略@Configuration注解
 *
 */
//@Configuration
@ConditionalOnClass(MyJsonWrapper.class)
public class MyJsonWrapperConfiguration {

    private MyJsonProperties myJsonProperties;

    public MyJsonWrapperConfiguration(MyJsonProperties myJsonProperties) {
        this.myJsonProperties = myJsonProperties;
    }

    @Bean
    @ConditionalOnProperty(name = "json.wrapper.enable")
    @ConditionalOnMissingBean(MyJsonWrapper.class)
    public MyJsonWrapper myJsonWrapper(){
        MyJsonWrapper myJsonWrapper = new MyJsonWrapper();
        myJsonWrapper.setPrefix(myJsonProperties.getPrefixName());
        myJsonWrapper.setSuffix(myJsonProperties.getSuffixName());
        return myJsonWrapper;
    }
}
