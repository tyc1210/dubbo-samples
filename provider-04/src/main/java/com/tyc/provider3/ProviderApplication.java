package com.tyc.provider3;

import org.apache.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.concurrent.TimeUnit;

/**
 * 类描述
 *
 * @author tyc
 * @version 1.0
 * @date 2022-06-24 15:12:26
 */
@SpringBootApplication
@DubboComponentScan
public class ProviderApplication {
    public static void main(String[] args) throws InterruptedException {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(ProviderApplication.class, args);
        while (true){
            TimeUnit.SECONDS.sleep(2);
            System.out.println(applicationContext.getEnvironment().getProperty("info"));
        }
    }
}
