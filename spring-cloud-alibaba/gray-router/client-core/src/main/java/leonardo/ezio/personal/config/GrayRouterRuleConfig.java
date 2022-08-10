package leonardo.ezio.personal.config;

import com.netflix.loadbalancer.IRule;
import leonardo.ezio.personal.rule.RibbonGrayRule;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description : 灰度路由配置
 * @Author : LeonardoEzio
 * @Date: 2022-08-09 16:23
 */
@Configuration
public class GrayRouterRuleConfig {

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnClass(RibbonGrayRule.class)
    public IRule ribbonRule() {
        return new RibbonGrayRule();
    }
}
