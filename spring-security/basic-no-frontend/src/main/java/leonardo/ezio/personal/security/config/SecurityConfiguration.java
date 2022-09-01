package leonardo.ezio.personal.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Description :
 * @Author : LeonardoEzio
 * @Date: 2022-07-20 10:30
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private DaoAuthenticationProvider authenticationProvider;

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.cors().and().csrf().disable();
//        http.authorizeRequests()
//                .antMatchers("/errorPage").permitAll()
//                .anyRequest()
//                .authenticated()
//                .and()
//                //开启form表单认证
//                .formLogin()
//                .loginPage("/login")
//                .defaultSuccessUrl("/index")
//                .failureUrl("/errorPage")
//                .and()
//                .logout()
//                .logoutUrl("/logout")
//                .logoutSuccessHandler((req,res,authentication)->{
//                    res.setContentType("application/json;charset=utf-8");
//                    PrintWriter out = res.getWriter();
//                    out.write("good bye");
//                    out.flush();
//                    out.close();
//                })
//                .and()
//                //基于httpBasic的认证方式
//                .httpBasic();
//    }


    /**
     * successHandler  failureHandler logoutSuccessHandler 等回调配置
     * */
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.cors().and().csrf().disable();
//        http.authorizeRequests()
//            .antMatchers("/errorPage").permitAll()
//            .anyRequest()
//            .authenticated()
//            .and()
//            //开启form表单认证
//            .formLogin()
//            .loginPage("/login")
//            .successHandler(((req, res, authentication) -> {
//                res.setContentType("application/json;charset=utf-8");
//                PrintWriter out = res.getWriter();
//                out.write("login success！welcome message from success handler");
//                out.flush();
//                out.close();
//            }))
//            .failureHandler(((req, res, e) -> {
//                res.setContentType("application/json;charset=utf-8");
//                PrintWriter out = res.getWriter();
//                out.write("login failed ! because of "+e.getMessage());
//                out.flush();
//                out.close();
//            }))
//            .and()
//            .logout()
//            .logoutUrl("/logout")
//            .logoutSuccessHandler((req,res,authentication)->{
//                res.setContentType("application/json;charset=utf-8");
//                PrintWriter out = res.getWriter();
//                out.write("good bye");
//                out.flush();
//                out.close();
//            })
//            .and()
//            //基于httpBasic的认证方式
//            .httpBasic();
//    }


    /**
     * successHandler  failureHandler logoutSuccessHandler 等回调配置
     * */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable();
        http.authorizeRequests()
            .antMatchers("/errorPage").permitAll()
            .anyRequest()
            .authenticated()
            .and()
            //开启form表单认证
            .formLogin()
            .loginPage("/login")
            .successHandler(successHandler())
            .failureHandler(failureHandler())
            .and()
            .logout()
            .logoutUrl("/logout")
            .logoutSuccessHandler(logoutSuccessHandler())
            .and()
            //基于httpBasic的认证方式
            .httpBasic();
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //密码加密方式1 NoOpPasswordEncoder
//        auth.inMemoryAuthentication().passwordEncoder(NoOpPasswordEncoder.getInstance())
//                .withUser("user1").password("password1").roles("ADMIN");
        //密码加密方式2 {noop}password1
//        auth.inMemoryAuthentication()
//                .withUser("user1").password("{noop}password1").roles("ADMIN");
        //基于数据库的认证
        auth.authenticationProvider(authenticationProvider);
    }

    @Bean
    public AuthenticationSuccessHandler successHandler(){
        return new AuthenticationSuccessHandler() {
            @Override
            public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
                httpServletResponse.setContentType("application/json;charset=utf-8");
                PrintWriter out = httpServletResponse.getWriter();
                out.write("login success！welcome message from success handler");
                out.flush();
                out.close();
            }
        };
    }


    @Bean
    public AuthenticationFailureHandler failureHandler(){
        return new AuthenticationFailureHandler() {
            @Override
            public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
                httpServletResponse.setContentType("application/json;charset=utf-8");
                PrintWriter out = httpServletResponse.getWriter();
                out.write("login failed ! because of "+e.getMessage());
                out.flush();
                out.close();
            }
        };
    }


    @Bean
    public LogoutSuccessHandler logoutSuccessHandler(){
        return new LogoutSuccessHandler() {
            @Override
            public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
                httpServletResponse.setContentType("application/json;charset=utf-8");
                PrintWriter out = httpServletResponse.getWriter();
                out.write("good bye");
                out.flush();
                out.close();
            }
        };
    }
}
