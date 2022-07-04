package com.tyc.order.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

public interface OrderMapper {
    @Insert("INSERT INTO `seata`.`order`(`productId`, `num`, `status`) VALUES (#{productId},#{num},#{status})")
    void add(@Param("productId") Long productId,@Param("num") Integer nums,@Param("status") String status);
}
