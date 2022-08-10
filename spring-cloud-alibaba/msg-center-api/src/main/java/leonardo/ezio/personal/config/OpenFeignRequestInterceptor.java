package leonardo.ezio.personal.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import leonardo.ezio.personal.common.GrayConstant;
import leonardo.ezio.personal.common.GrayContextHolder;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * @Description : openfeign调用拦截
 * @Author : LeonardoEzio
 * @Date: 2022-08-09 18:18
 */
@Configuration
public class OpenFeignRequestInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate requestTemplate) {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (null != servletRequestAttributes){
            HttpServletRequest request = servletRequestAttributes.getRequest();
            Enumeration<String> headerNames = request.getHeaderNames();
            while (headerNames.hasMoreElements()){
                String headName = headerNames.nextElement();
                String headValue = request.getHeader(headName);
                requestTemplate.header(headName, headValue);
                if (GrayConstant.GRAY_HEAD.equals(headName)){
                    GrayContextHolder.putContext(headValue);
                }
            }
        }
    }
}
