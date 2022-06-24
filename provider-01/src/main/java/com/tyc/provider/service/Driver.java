package com.tyc.provider.service;

import org.apache.dubbo.common.extension.SPI;
/**
 * 测试 Dubbo SPI
 */
@SPI
public interface Driver {
    String connect();
}
