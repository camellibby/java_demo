package com.camellibby.business.controller;

import com.camellibby.business.service.BusinessService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/business")
@Slf4j
@AllArgsConstructor
public class BusinessController {
    private final BusinessService businessService;

    @RequestMapping("commit")
    public String commit() {
        businessService.create(1L, 1L, 1, 10L);
        return "commit success";
    }

    @RequestMapping("rollback")
    public String rollback() {
        businessService.create(1L, 1L, 101, 10L);
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
        businessService.create(userId, productId, count, money);
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
        businessService.update(userId, money, status);
        return "订单状态修改成功";
    }
}
