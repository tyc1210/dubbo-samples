package com.tyc.stock.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface StockMapper {
    @Update("update stock set num = num - #{num} where product_id = #{id}")
    void update(@Param("id") Long id,@Param("num") Integer num);
}
