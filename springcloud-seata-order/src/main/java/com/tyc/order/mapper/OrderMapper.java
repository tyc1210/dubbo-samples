package com.tyc.order.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

public interface OrderMapper {
    @Insert("INSERT INTO `seata`.`order`(`productId`, `nums`, `status`) VALUES (#{productId},#{nums},#{status})")
    void add(@Param("productId") Long productId,@Param("nums") Integer nums,@Param("status") String status);
}
