package com.tyc.provider3.service.dubbo;

import com.tyc.api.IHelloService;
import org.apache.dubbo.config.annotation.Service;
import org.apache.dubbo.rpc.cluster.loadbalance.ConsistentHashLoadBalance;
import org.apache.dubbo.rpc.cluster.support.FailoverCluster;
import org.springframework.beans.factory.annotation.Value;

/**
 * 类描述
 *
 * @author tyc
 * @version 1.0
 * @date 2022-06-21 13:35:15
 */

@Service
public class HelloServiceImpl implements IHelloService {
    @Value("${dubbo.application.name}")
    private String serviceName;

    @Override
    public String sayHello(String name) {
        return String.format("[%s] say: hello %s!",serviceName, name);
    }
}
