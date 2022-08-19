package leonardo.ezio.personal.filter;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import leonardo.ezio.personal.common.GrayConstant;
import leonardo.ezio.personal.common.GrayContextHolder;
import leonardo.ezio.personal.rule.GrayRule;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * @Description : 灰度过滤器，给需要灰度的流量打上标识
 * @Author : LeonardoEzio
 * @Date: 2022-08-08 15:09
 */
@Component
public class GrayFilter implements GlobalFilter, Ordered {

    private static final Log log = LogFactory.getLog(GrayFilter.class);

    private static final HazelcastInstance HAZELCAST_INSTANCE = Hazelcast.newHazelcastInstance();

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //从token中解析用户名匹配灰度规则
        ServerHttpRequest request = exchange.getRequest();
        String authorization = request.getHeaders().getFirst("Authorization");
        List<GrayRule> rules = HAZELCAST_INSTANCE.getList(GrayConstant.GRAY_RULE);
        boolean needGrayRoute = false;
        String targetVersion = "";
        for (GrayRule rule : rules) {
            if (rule.getRouteKey().equals("username")){
                if (rule.getMatchRule().equals("equals") && authorization.equals(rule.getMatchValue())){
                    needGrayRoute = true;
                    targetVersion = rule.getTargetVersion();
                    break;
                } else if (rule.getMatchRule().equals("like") && authorization.startsWith(rule.getMatchValue())){
                    needGrayRoute = true;
                    targetVersion = rule.getTargetVersion();
                    break;
                }
            }else if (rule.getMatchRule().equals("all")){
                needGrayRoute = true;
                targetVersion = rule.getTargetVersion();
                break;
            }
        }

        if (needGrayRoute){
            log.info(String.format("request need router ! gray version : %s", targetVersion));
            GrayContextHolder.putContext(targetVersion);
            // request请求头不支持直接修改 需要通过mutate修改，ServerWebExchange也是如此
            exchange = exchange.mutate().request(request.mutate().header(GrayConstant.GRAY_HEAD, targetVersion).build()).build();
        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }

}
