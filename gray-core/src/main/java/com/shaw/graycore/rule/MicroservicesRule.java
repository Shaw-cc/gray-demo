package com.shaw.graycore.rule;

import com.alibaba.cloud.nacos.NacosDiscoveryProperties;
import com.alibaba.cloud.nacos.ribbon.ExtendBalancer;
import com.alibaba.cloud.nacos.ribbon.NacosRule;
import com.alibaba.cloud.nacos.ribbon.NacosServer;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;
import com.netflix.loadbalancer.DynamicServerListLoadBalancer;
import com.netflix.loadbalancer.Server;
import com.shaw.graycore.service.GrayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description: 微服务灰度规则
 * @Author: Shaw
 * @Date: 2022/8/12 00:30
 */
@Component
@Slf4j
public class MicroservicesRule extends NacosRule {

    public static final String GARY = "gray";
    public static final String PRODUCT = "product";
    public static final String GRAY_STATE = "grayState";

    @Value("${app.version}")
    String version;

    @Autowired
    private NacosDiscoveryProperties nacosDiscoveryProperties;

    @Autowired
    GrayService grayService;

    /**
     * A->B
     * 1 从ctcd获取灰度规则、版本
     * 2 根据版本走向不同的节点
     * 3 根据灰度状态
     *
     * @param o not import
     * @return
     */
    @Override
    public Server choose(Object o) {
        try {
            DynamicServerListLoadBalancer loadBalancer = (DynamicServerListLoadBalancer) getLoadBalancer();
            String name = loadBalancer.getName();
            NamingService namingService = this.nacosDiscoveryProperties.namingServiceInstance();
            List<Instance> proInstances = namingService.selectInstances(name, true).stream().filter(MicroservicesRule::isProductInstance).collect(Collectors.toList());
            List<Instance> grayInstances = namingService.selectInstances(name, true).stream().filter(MicroservicesRule::isGrayInstance).collect(Collectors.toList());
            List<Instance> instances = proInstances;
            if (version.equals("1.0")) {
                log.info("当前为生产节点");
            } else {
                boolean match = grayService.isMatch();
                log.info("当前为灰度节点,是否满足灰度规则:{}", match);
                if (match) {
                    instances = grayInstances;
                }
            }
            Instance instance = ExtendBalancer.getHostByRandomWeight2(instances);
            return new NacosServer(instance);
        } catch (Exception e) {
            return null;
        }
    }

    public static boolean isGrayInstance(Instance instance) {
        return getInstanceState(instance).equals(GARY);
    }

    public static boolean isProductInstance(Instance instance) {
        return getInstanceState(instance).equals(PRODUCT);
    }

    /**
     * 获取节点灰度状态
     *
     * @param instance 节点
     * @return 灰度or生产
     */
    public static String getInstanceState(Instance instance) {
        return instance.getMetadata().getOrDefault(GRAY_STATE, PRODUCT);
    }
}
