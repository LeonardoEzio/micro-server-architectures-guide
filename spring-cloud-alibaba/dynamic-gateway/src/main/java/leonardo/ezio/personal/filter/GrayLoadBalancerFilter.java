package leonardo.ezio.personal.filter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerUriTools;
import org.springframework.cloud.client.loadbalancer.reactive.DefaultRequest;
import org.springframework.cloud.client.loadbalancer.reactive.Request;
import org.springframework.cloud.client.loadbalancer.reactive.Response;
import org.springframework.cloud.gateway.config.LoadBalancerProperties;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.support.DelegatingServiceInstance;
import org.springframework.cloud.gateway.support.NotFoundException;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.cloud.loadbalancer.core.ReactorLoadBalancer;
import org.springframework.cloud.loadbalancer.support.LoadBalancerClientFactory;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description : 灰度路由过滤器
 * @Author : LeonardoEzio
 * @Date: 2022-07-28 15:57
 */
@Component
public class GrayLoadBalancerFilter implements GlobalFilter, Ordered {

    private static final Log log = LogFactory.getLog(GrayLoadBalancerFilter.class);

    private static final List<String> GRAY_USER = new ArrayList<String>(){{
        add("aaa");
    }};

    private final LoadBalancerClientFactory clientFactory;

    private LoadBalancerProperties properties;

    public GrayLoadBalancerFilter(LoadBalancerClientFactory clientFactory, LoadBalancerProperties properties) {
        this.clientFactory = clientFactory;
        this.properties = properties;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        URI url = (URI)exchange.getAttribute(ServerWebExchangeUtils.GATEWAY_REQUEST_URL_ATTR);
        String schemePrefix = (String)exchange.getAttribute(ServerWebExchangeUtils.GATEWAY_SCHEME_PREFIX_ATTR);

        //从token中解析用户名匹配灰度规则
        ServerHttpRequest request = exchange.getRequest();
        String authorization = request.getHeaders().getFirst("Authorization");
        if (GRAY_USER.contains(authorization)){
            // request请求头不支持直接修改 需要通过mutate修改，ServerWebExchange也是如此
            exchange = exchange.mutate().request(request.mutate().header("grayVersion", "2.0.0").build()).build();
        }

        final ServerWebExchange finalExchange = exchange;
        return this.choose(exchange).doOnNext((response) ->{
            if (!response.hasServer()) {
                throw NotFoundException.create(this.properties.isUse404(), "Unable to find instance for " + url.getHost());
            } else {
                URI uri = finalExchange.getRequest().getURI();
                String overrideScheme = null;
                if (schemePrefix != null) {
                    overrideScheme = url.getScheme();
                }
                DelegatingServiceInstance serviceInstance = new DelegatingServiceInstance((ServiceInstance)response.getServer(), overrideScheme);
                URI requestUrl = this.reconstructURI(serviceInstance, uri);
                if (log.isTraceEnabled()) {
                    log.trace("LoadBalancerClientFilter url chosen: " + requestUrl);
                }
                finalExchange.getAttributes().put(ServerWebExchangeUtils.GATEWAY_REQUEST_URL_ATTR, requestUrl);
            }
        }).then(chain.filter(exchange));
    }


    private Mono<Response<ServiceInstance>> choose(ServerWebExchange exchange) {
        URI uri = (URI)exchange.getAttribute(ServerWebExchangeUtils.GATEWAY_REQUEST_URL_ATTR);
        ReactorLoadBalancer<ServiceInstance> loadBalancer = (ReactorLoadBalancer)this.clientFactory.getInstance(uri.getHost(), ReactorLoadBalancer.class, new Class[]{ServiceInstance.class});
        if (loadBalancer == null) {
            throw new NotFoundException("No loadbalancer available for " + uri.getHost());
        } else {
            return loadBalancer.choose(this.createRequest(exchange));
        }
    }


    private Request createRequest(ServerWebExchange exchange) {
        HttpHeaders headers = exchange.getRequest().getHeaders();
        Request<HttpHeaders> request = new DefaultRequest<>(headers);
        return request;
    }

    protected URI reconstructURI(ServiceInstance serviceInstance, URI original) {
        return LoadBalancerUriTools.reconstructURI(serviceInstance, original);
    }

    @Override
    public int getOrder() {
        return 10150;
    }

}
