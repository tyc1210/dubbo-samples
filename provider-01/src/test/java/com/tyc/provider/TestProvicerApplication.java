package com.tyc.provider;

import com.tyc.provider.service.Driver;
import org.apache.dubbo.common.extension.ExtensionLoader;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * 类描述
 *
 * @author tyc
 * @version 1.0
 * @date 2022-06-24 10:22:15
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestProvicerApplication {
    @Test
    public void testDubboSPI() {
        ExtensionLoader<Driver> extensionLoader = ExtensionLoader.getExtensionLoader(Driver.class);
        Driver mySqlDriver = extensionLoader.getExtension("mySqlDriver");
        System.out.println(mySqlDriver.connect());
    }
}
