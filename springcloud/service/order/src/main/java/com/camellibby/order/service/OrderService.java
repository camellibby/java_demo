package com.camellibby.order.service;

public interface OrderService {
    /**
     * 创建订单
     *
     * @param userId
     * @param productId
     * @param count
     * @param money
     */
    void create(Long userId, Long productId, Integer count, Long money);

    /**
     * 修改订单状态
     *
     * @param userId
     * @param money
     * @param status
     */
    void update(Long userId, Long money, Integer status);
}
