package leonardo.ezio.personal.stater.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @Author : LeonardoEzio
 * @Date: 2021-06-11 15:11
 *
 * 此配置类可以和  {@link MyJsonWrapperConfiguration} 合并
 *
 */
@Configuration
@ConfigurationProperties(prefix = "json.wrapper")
@EnableConfigurationProperties(MyJsonProperties.class)
public class MyJsonProperties {

    public static final String DEFAULT_PREFIX = "^";

    public static final String DEFAULT_SUFFIX = "@";

    private String prefixName = DEFAULT_PREFIX;

    private String suffixName = DEFAULT_SUFFIX;

    public String getPrefixName() {
        return prefixName;
    }

    public void setPrefixName(String prefixName) {
        this.prefixName = prefixName;
    }

    public String getSuffixName() {
        return suffixName;
    }

    public void setSuffixName(String suffixName) {
        this.suffixName = suffixName;
    }
}
