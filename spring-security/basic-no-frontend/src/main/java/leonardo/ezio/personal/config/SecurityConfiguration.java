package leonardo.ezio.personal.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

import java.io.PrintWriter;

/**
 * @Description :
 * @Author : LeonardoEzio
 * @Date: 2022-07-20 10:30
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

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
                .defaultSuccessUrl("/index")
                .failureUrl("/errorPage")
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessHandler((req,res,authentication)->{
                    res.setContentType("application/json;charset=utf-8");
                    PrintWriter out = res.getWriter();
                    out.write("good bye");
                    out.flush();
                    out.close();
                })
                .and()
                //基于httpBasic的认证方式
                .httpBasic();
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().passwordEncoder(NoOpPasswordEncoder.getInstance())
                .withUser("user1").password("{noop}password1").roles("ADMIN");
    }
}
