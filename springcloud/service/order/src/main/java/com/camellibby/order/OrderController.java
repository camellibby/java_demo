package com.camellibby.order;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
@Slf4j
@AllArgsConstructor
public class OrderController {
    private final OrderService orderService;

    /**
     * 创建订单
     *
     * @param order
     * @return
     */
    @GetMapping("create")
    public String create(Order order) {
        orderService.create(order);
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
