package com.camellibby.order;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "Storage", path = "stock")
public interface StorageApi {
    /**
     * 扣减库存
     *
     * @param productId
     * @param count
     * @return
     */
    @GetMapping(value = "/decrease")
    String decrease(@RequestParam("productId") Long productId, @RequestParam("count") Integer count);
}
