package leonardo.ezio.personal.filter;

import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;

/**
 * @Description :
 * @Author : LeonardoEzio
 * @Date: 2022-08-10 11:56
 */
@Component
@ServletComponentScan
public class HystrixContextInitFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HystrixRequestContext context = HystrixRequestContext.initializeContext();
        try {
            filterChain.doFilter(servletRequest, servletResponse);
        } finally {
            context.shutdown();
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}
