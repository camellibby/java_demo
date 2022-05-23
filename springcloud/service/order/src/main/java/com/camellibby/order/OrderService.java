package com.camellibby.order;

public interface OrderService {
    /**
     * 创建订单
     *
     * @param order
     * @return
     */
    void create(Order order);

    /**
     * 修改订单状态
     *
     * @param userId
     * @param money
     * @param status
     */
    void update(Long userId, Long money, Integer status);
}
