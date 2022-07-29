package leonardo.ezio.personal.loadbalance;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.reactive.DefaultResponse;
import org.springframework.cloud.client.loadbalancer.reactive.EmptyResponse;
import org.springframework.cloud.client.loadbalancer.reactive.Request;
import org.springframework.cloud.client.loadbalancer.reactive.Response;
import org.springframework.cloud.loadbalancer.core.NoopServiceInstanceListSupplier;
import org.springframework.cloud.loadbalancer.core.ReactorServiceInstanceLoadBalancer;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.http.HttpHeaders;
import org.springframework.util.StringUtils;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * @Description : 灰度负载均衡器
 * @Author : LeonardoEzio
 * @Date: 2022-07-28 15:12
 */
public class GrayRoundRobinLoadBalancer implements ReactorServiceInstanceLoadBalancer {

    private static final Log log = LogFactory.getLog(GrayRoundRobinLoadBalancer.class);

    private ObjectProvider<ServiceInstanceListSupplier> serviceInstanceListSupplierProvider;

    private final String serviceId;

    public GrayRoundRobinLoadBalancer(ObjectProvider<ServiceInstanceListSupplier> serviceInstanceListSupplierProvider, String serviceId) {
        this.serviceInstanceListSupplierProvider = serviceInstanceListSupplierProvider;
        this.serviceId = serviceId;
    }

    @Override
    public Mono<Response<ServiceInstance>> choose(Request request) {
        ServiceInstanceListSupplier instanceListSupplier = serviceInstanceListSupplierProvider.getIfAvailable(NoopServiceInstanceListSupplier::new);
        return instanceListSupplier.get().next().map(serviceInstances -> getInstanceResponse(request, serviceInstances));
    }


    private Response<ServiceInstance> getInstanceResponse(Request request,List<ServiceInstance> instances) {
        HttpHeaders headers = (HttpHeaders) request.getContext();

        //过滤出符合目标的版本
        String grayVersion = headers.getFirst("grayVersion");
        if (!StringUtils.isEmpty(grayVersion)){
            instances = instances.stream().filter(instance-> {
                String version = instance.getMetadata().get("version");
                if (grayVersion.equals(version)){
                    return true;
                }
                return false;
            }).collect(Collectors.toList());
        }

        //轮询负载
        if (instances.isEmpty()) {
            log.warn("No servers available for service: " + this.serviceId);
            return new EmptyResponse();
        } else {
            int size = instances.size();
            Random random = new Random();
            ServiceInstance instance = (ServiceInstance)instances.get(random.nextInt(size));
            return new DefaultResponse(instance);
        }
    }
}
