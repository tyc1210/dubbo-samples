package com.tyc.provider.service.dubbo;

import com.tyc.api.IHelloService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Value;

/**
 * 类描述
 *
 * @author tyc
 * @version 1.0
 * @date 2022-06-21 13:35:15
 */
@Service
class HelloServiceImpl implements IHelloService {
    @Value("${dubbo.application.name}")
    private String serviceName;

    @Override
    public String sayHello(String name) {
        return String.format("[%s] say: hello %s!",serviceName, name);
    }
}
