package leonardo.ezio.personal.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @Description : 安全配置
 * @Author : LeonardoEzio
 * @Date: 2022-07-14 17:51
 *
 * questions :
 * what is the difference between HttpSecurity and WebSecurity
 */
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 通过 authorizeRequests() 方法对所有访问HTTP端点的HttpServletRequest 进行限制
        http.authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                // formLogin() 语句用于指定使用表单登录作为认证方式，也就是会弹出一个登录界面；
                .formLogin()
                .and()
//                //httpBasic() 语句表示可以使用 HTTP 基础认证（Basic Authentication）方法来完成认证
                .httpBasic();
    }
}
