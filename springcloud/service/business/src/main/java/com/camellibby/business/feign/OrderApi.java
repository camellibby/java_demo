package com.camellibby.business.feign;

import com.camellibby.business.entity.Order;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "order", path = "order")
public interface OrderApi {
    /**
     * 创建订单
     * http://localhost:8964/order/create?userId=1&productId=1&count=10&money=100
     *
     * @param userId
     * @param productId
     * @param count
     * @param money
     * @return
     */
    @RequestMapping("/create")
    String create(@RequestParam("userId") Long userId, @RequestParam("productId") Long productId,
                  @RequestParam("count") Integer count,@RequestParam("money") Long money);

    /**
     * 修改订单金额
     *
     * @param userId
     * @param money
     * @param status
     * @return
     */
    @RequestMapping("/update")
    String update(@RequestParam("userId") Long userId, @RequestParam("money") Long money,
                  @RequestParam("status") Integer status);
}
