package leonardo.ezio.personal.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionWriter;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * @Description :
 * @Author : LeonardoEzio
 * @Date: 2022-07-25 14:15
 */
@Component
public class DynamicRouteService implements ApplicationEventPublisherAware {

    private ApplicationEventPublisher eventPublisher;

    @Autowired
    private RouteDefinitionWriter routeDefinitionWriter;

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.eventPublisher = applicationEventPublisher;
    }

    public void add(RouteDefinition routeDefinition){
        routeDefinitionWriter.save(Mono.just(routeDefinition)).subscribe();
        this.eventPublisher.publishEvent(new RefreshRoutesEvent(this));
    }

    public void update(RouteDefinition routeDefinition){
        routeDefinitionWriter.delete(Mono.just(routeDefinition.getId()));
        routeDefinitionWriter.save(Mono.just(routeDefinition)).subscribe();
        this.eventPublisher.publishEvent(new RefreshRoutesEvent(this));
    }

    public void updateList(List<RouteDefinition> routeDefinitions){
        routeDefinitions.forEach(this::update);
    }

    public void delete(String id){
        routeDefinitionWriter.delete(Mono.just(id));
    }
}
