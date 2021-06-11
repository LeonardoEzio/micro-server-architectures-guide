package leonardo.ezio.personal.stater.config;

import leonardo.ezio.personal.stater.wrapper.MyJsonWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author : LeonardoEzio
 * @Date: 2021-06-11 15:08
 */
@Configuration
@ConditionalOnClass(MyJsonWrapper.class)
@EnableConfigurationProperties(MyJsonProperties.class)
public class MyJsonWrapperConfiguration {

    @Autowired
    private MyJsonProperties myJsonProperties;

    @Bean
    @ConditionalOnProperty(name = "json.wrapper.enable",matchIfMissing = true)
    @ConditionalOnMissingBean(MyJsonWrapper.class)
    public MyJsonWrapper myJsonWrapper(){
        MyJsonWrapper myJsonWrapper = new MyJsonWrapper();
        myJsonWrapper.setPrefix(myJsonProperties.getPrefixName());
        myJsonWrapper.setSuffix(myJsonProperties.getSuffixName());
        return myJsonWrapper;
    }
}
