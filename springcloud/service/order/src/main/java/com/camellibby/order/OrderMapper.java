package com.camellibby.order;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface OrderMapper {

    /**
     * 创建订单
     *
     * @param order
     * @return
     */
    void create(Order order);

    /**
     * 修改订单金额
     *
     * @param userId
     * @param money
     */
    void update(@Param("userId") Long userId, @Param("money") Long money, @Param("status") Integer status);
}
