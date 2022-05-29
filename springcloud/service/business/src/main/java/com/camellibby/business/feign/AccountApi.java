package com.camellibby.business.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "account", path = "/account")
public interface AccountApi {
    /**
     * 扣减账户余额
     *
     * @param userId 用户id
     * @param money  金额
     * @return
     */
    @GetMapping("/decrease")
    String decrease(@RequestParam("userId") Long userId, @RequestParam("money") Long money);
}
