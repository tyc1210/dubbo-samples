package com.tyc.provider.service.impl;

import com.tyc.provider.service.Driver;

/**
 * 类描述
 *
 * @author tyc
 * @version 1.0
 * @date 2022-06-24 10:19:10
 */
public class MySqlDriver implements Driver {
    @Override
    public String connect() {
        return "mysql 连接数据库";
    }
}
