package leonardo.ezio.personal.filter;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RoundRobinRule;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description : 灰度过滤器，给需要灰度的流量打上标识
 * @Author : LeonardoEzio
 * @Date: 2022-08-08 15:09
 */
@Component
public class GrayFilter implements GlobalFilter, Ordered {

    private static final Log log = LogFactory.getLog(GrayFilter.class);

    private static final List<String> GRAY_USER = new ArrayList<String>(){{
        add("aaa");
    }};

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //从token中解析用户名匹配灰度规则
        ServerHttpRequest request = exchange.getRequest();
        String authorization = request.getHeaders().getFirst("Authorization");
        if (GRAY_USER.contains(authorization)){
            // request请求头不支持直接修改 需要通过mutate修改，ServerWebExchange也是如此
            exchange = exchange.mutate().request(request.mutate().header("grayVersion", "2.0.0").build()).build();
        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }

    @Bean
    @ConditionalOnMissingBean
    public IRule ribbonRule() {
        return new RoundRobinRule();
    }
}
