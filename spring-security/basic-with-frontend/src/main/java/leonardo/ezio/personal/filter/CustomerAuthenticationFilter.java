package leonardo.ezio.personal.filter;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description :
 * @Author : LeonardoEzio
 * @Date: 2022-07-19 15:58
 */
public class CustomerAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    public CustomerAuthenticationFilter() {
        super(new AntPathRequestMatcher("/customerLogin", "POST"));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws AuthenticationException, IOException, ServletException {
        return null;
    }
}
