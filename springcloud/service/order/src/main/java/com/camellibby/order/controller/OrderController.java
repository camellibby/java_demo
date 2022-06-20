package com.camellibby.order.controller;

import com.camellibby.order.service.OrderService;
import com.camellibby.order.entity.Order;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
@Slf4j
@AllArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @RequestMapping("commit")
    public String commit() {
        orderService.create(1L, 1L, 1, 10L);
        return "commit success";
    }

    @RequestMapping("rollback")
    public String rollback() {
        orderService.create(1L, 1L, 101, 10L);
        return "rollback success";
    }

    /**
     * 创建订单
     * http://localhost:8962/order/create?userId=1&productId=1&count=10&money=100
     *
     * @param userId
     * @param productId
     * @param count
     * @param money
     * @return
     */
    @RequestMapping("create")
    public String create(@RequestParam("userId") Long userId, @RequestParam("productId") Long productId,
                         @RequestParam("count") Integer count, @RequestParam("money") Long money) {
        orderService.create(userId, productId, count, money);
        return "Create order success";
    }

    /**
     * 修改订单状态
     *
     * @param userId
     * @param money
     * @param status
     * @return
     */
    @RequestMapping("update")
    String update(@RequestParam("userId") Long userId, @RequestParam("money") Long money,
                  @RequestParam("status") Integer status) {
        orderService.update(userId, money, status);
        return "订单状态修改成功";
    }
}
