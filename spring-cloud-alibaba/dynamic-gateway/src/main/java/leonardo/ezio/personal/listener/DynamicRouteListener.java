package leonardo.ezio.personal.listener;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;
import leonardo.ezio.personal.component.DynamicRouteService;
import leonardo.ezio.personal.config.DynamicRouteConfig;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Properties;
import java.util.concurrent.Executor;

/**
 * @Description :
 * @Author : LeonardoEzio
 * @Date: 2022-07-25 15:35
 */
@Configuration
public class DynamicRouteListener implements InitializingBean {

    private static final Logger log = LoggerFactory.getLogger(DynamicRouteListener.class);

    @Autowired
    private DynamicRouteConfig dynamicRouteConfig;

    @Autowired
    private DynamicRouteService dynamicRouteService;

    @Override
    public void afterPropertiesSet() throws Exception {
        listenerConfigUpdate();
    }

    private void listenerConfigUpdate(){
        try {
            Properties properties = new Properties();
            properties.put("serverAddr", dynamicRouteConfig.getAddress());
            properties.put("namespace", dynamicRouteConfig.getNamespace());
            ConfigService configService = NacosFactory.createConfigService(properties);
            configService.addListener(dynamicRouteConfig.getDataId(), dynamicRouteConfig.getGroupId(), new Listener() {
                @Override
                public Executor getExecutor() {
                    return null;
                }

                @Override
                public void receiveConfigInfo(String configInfo) {
                    log.info("router config from dataId : {} ; groupId : {} ; config info : {} ",dynamicRouteConfig.getDataId(),dynamicRouteConfig.getGroupId(),configInfo);
                    List<RouteDefinition> routeDefinitions = JSONObject.parseArray(configInfo, RouteDefinition.class);
                    routeDefinitions.forEach(dynamicRouteService::update);
                }
            });
        } catch (NacosException e) {
            log.error("config update listener failed !!! exception info : {}", ExceptionUtils.getStackTrace(e));
        }
    }
}
