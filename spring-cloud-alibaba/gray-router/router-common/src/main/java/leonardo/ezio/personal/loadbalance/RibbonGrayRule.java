package leonardo.ezio.personal.loadbalance;


import com.alibaba.cloud.nacos.ribbon.NacosServer;
import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.Server;
import leonardo.ezio.personal.common.GrayConstant;
import leonardo.ezio.personal.common.GrayContextHolder;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;


/**
 * @Description : 轮询灰度路由规则
 * @Author : LeonardoEzio
 * @Date: 2022-08-09 15:42
 */
public class RibbonGrayRule extends AbstractLoadBalancerRule {

    private AtomicInteger position;

    private AtomicInteger grayPosition;

    public RibbonGrayRule() {
        this.position = new AtomicInteger(0);
        this.grayPosition = new AtomicInteger(0);
    }

    public RibbonGrayRule(ILoadBalancer lb) {
        this();
        this.setLoadBalancer(lb);
    }

    @Override
    public Server choose(Object o) {
        Server chooseServer = null;
        try {
            //从ThreadLocal中获取灰度标记
            String grayTag = GrayContextHolder.getContext();
            //获取所有可用服务
            List<Server> serverList = this.getLoadBalancer().getReachableServers();
            //灰度发布的服务
            List<Server> grayServerList = new ArrayList<>();
            for(Server server : serverList) {
                NacosServer nacosServer = (NacosServer) server;
                //从nacos中获取元素剧进行匹配
                if(nacosServer.getMetadata().containsKey(GrayConstant.SERVER_VERSION) && nacosServer.getMetadata().get(GrayConstant.SERVER_VERSION).equals(grayTag)) {
                    grayServerList.add(server);
                }
            }
            //如果被标记为灰度发布，则调用灰度发布的服务
            if(StringUtils.isNotEmpty(grayTag)) {
                if (!CollectionUtils.isEmpty(grayServerList)){
                    chooseServer = grayServerList.get(grayPosition.getAndIncrement() % grayServerList.size());
                }
            } else {
                chooseServer = serverList.get(position.getAndIncrement() % serverList.size());
            }
        } finally {
            //清除灰度标记
            GrayContextHolder.removeContext();
            return chooseServer;
        }
    }


    @Override
    public void initWithNiwsConfig(IClientConfig clientConfig) {

    }
}
