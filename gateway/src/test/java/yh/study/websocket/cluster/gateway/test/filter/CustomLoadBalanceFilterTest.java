package yh.study.websocket.cluster.gateway.test.filter;

import com.alibaba.cloud.nacos.NacosDiscoveryProperties;
import com.alibaba.cloud.nacos.NacosServiceManager;
import com.alibaba.cloud.nacos.discovery.NacosDiscoveryClient;
import com.alibaba.cloud.nacos.discovery.NacosServiceDiscovery;
import yh.study.websocket.cluster.gateway.config.WebSocketProperties;
import yh.study.websocket.cluster.gateway.server.ServiceNode;
import yh.study.websocket.cluster.common.hashring.ConsistentHashRouter;
import org.junit.jupiter.api.Test;
import org.springframework.cloud.gateway.config.LoadBalancerProperties;
import org.springframework.cloud.loadbalancer.blocking.client.BlockingLoadBalancerClient;
import org.springframework.cloud.loadbalancer.support.LoadBalancerClientFactory;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SuppressWarnings("deprecation")
class CustomLoadBalanceFilterTest {
    @Test
    void testConstructor() {
        BlockingLoadBalancerClient loadBalancer = new BlockingLoadBalancerClient(new LoadBalancerClientFactory());

        LoadBalancerProperties loadBalancerProperties = new LoadBalancerProperties();
        loadBalancerProperties.setUse404(true);
        ConsistentHashRouter<ServiceNode> consistentHashRouter = new ConsistentHashRouter<>(new ArrayList<>(), 3);

        WebSocketProperties webSocketProperties = new WebSocketProperties();
        NacosDiscoveryProperties discoveryProperties = new NacosDiscoveryProperties();
        assertTrue(
                (new CustomLoadBalanceFilter(loadBalancer, loadBalancerProperties, consistentHashRouter, webSocketProperties,
                        new NacosDiscoveryClient(
                                new NacosServiceDiscovery(discoveryProperties, new NacosServiceManager())))).webSocketProperties
                        .isEmpty());
    }
}

