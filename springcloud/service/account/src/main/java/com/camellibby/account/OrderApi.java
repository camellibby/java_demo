package com.camellibby.account;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "order", path = "order")
public interface OrderApi {

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
