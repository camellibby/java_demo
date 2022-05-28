package com.camellibby.order.controller;

import com.camellibby.order.service.OrderService;
import com.camellibby.order.entity.Order;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;

@RestController
@RequestMapping("/order")
@Slf4j
@AllArgsConstructor
public class OrderController {
    public static void main(String[] args) {
        Set<String> columns = new TreeSet<>(String.CASE_INSENSITIVE_ORDER);
        columns.addAll(Arrays.asList("a,b,c,d,e".split(",")));
        columns.addAll(Arrays.asList("a,b,c,1,2,3".split(",")));
        for (String column : columns) {
            System.out.println(column);
        }
    }

    private final OrderService orderService;

    /**
     * 创建订单
     * http://localhost:8962/order/create?userId=1&productId=1&count=10&money=100
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
