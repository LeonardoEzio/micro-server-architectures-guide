package leonardo.ezio.personal.config;

import leonardo.ezio.personal.filter.CustomerAuthenticationFilter;
import leonardo.ezio.personal.handler.CustomerAuthenticationSuccessHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

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
    public void configure(WebSecurity web) throws Exception {
        //静态资源被拦截问题
        web.ignoring().antMatchers("/css/**", "/images/**", "/js/**", "/code/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilterBefore(new CustomerAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        // 通过 authorizeRequests() 方法对所有访问HTTP端点的HttpServletRequest 进行限制
        // formLogin() 语句用于指定使用表单登录作为认证方式，也就是会弹出一个登录界面；
        http.formLogin()
        .loginPage("/toLoginPage")
        //处理登录请求的地址，注意非我们定义controller中的RequestMapping地址，而是实现了AbstractAuthenticationProcessingFilter中所指定的地址
        .loginProcessingUrl("/customerLogin")
        .successForwardUrl("/")
        //定义登录成功处理器
        .successHandler(new CustomerAuthenticationSuccessHandler())
        .and()
        .authorizeRequests()
        .antMatchers("/toLoginPage","/customerLogin").permitAll()
        .anyRequest().authenticated()
        .and()
        //httpBasic() 语句表示可以使用 HTTP 基础认证（Basic Authentication）方法来完成认证
        .httpBasic();

        http.cors().configurationSource(corsConfigurationSource());
    }


    /**
     * 跨域配置信息源
     */
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        //允许跨域的站点
        corsConfiguration.addAllowedOrigin("*");
        //允许跨域的http方法
        corsConfiguration.addAllowedMethod("*");
        // 允许跨域的请求头
        corsConfiguration.addAllowedHeader("*");
        // 允许带凭证
        corsConfiguration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
        // 对所有url都生效
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);

        return urlBasedCorsConfigurationSource;
    }
}
