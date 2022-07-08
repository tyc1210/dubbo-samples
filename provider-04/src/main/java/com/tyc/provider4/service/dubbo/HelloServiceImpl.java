package com.tyc.provider4.service.dubbo;

import com.tyc.api.IHelloService;
import com.tyc.provider4.nacos.TestRefreshScope;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private TestRefreshScope testRefreshScope;

    public TestRefreshScope getTestRefreshScope() {
        return testRefreshScope;
    }

    @Override
    public String sayHello(String name) {
        return String.format("[%s] say: hello %s!",serviceName, name);
    }
}
