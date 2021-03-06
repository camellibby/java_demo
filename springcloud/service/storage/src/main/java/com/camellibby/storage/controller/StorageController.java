package com.camellibby.storage.controller;

import com.camellibby.storage.service.StorageService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/storage")
public class StorageController {
    private final StorageService storageService;

    /**
     * 扣减库存
     *
     * @param productId 产品id
     * @param count     数量
     * @return
     */
    @RequestMapping("decrease")
    public String decrease(@RequestParam("productId") Long productId, @RequestParam("count") Integer count) {
        storageService.decrease(productId, count);
        return "Decrease stock success";
    }
}
