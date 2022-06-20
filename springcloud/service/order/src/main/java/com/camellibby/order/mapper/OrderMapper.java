package com.camellibby.order.mapper;

import com.camellibby.order.entity.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface OrderMapper {

    /**
     * 创建订单
     *
     * @param userId
     * @param productId
     * @param count
     * @param money
     * @return
     */
    void create(@Param("userId") Long userId, @Param("productId") Long productId, @Param("count") Integer count, @Param("money") Long money);

    /**
     * 修改订单金额
     *
     * @param userId
     * @param money
     */
    void update(@Param("userId") Long userId, @Param("money") Long money, @Param("status") Integer status);
}
