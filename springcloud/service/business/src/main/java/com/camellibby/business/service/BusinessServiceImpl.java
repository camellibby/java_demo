package com.camellibby.business.service;

import com.camellibby.business.feign.AccountApi;
import com.camellibby.business.feign.OrderApi;
import com.camellibby.business.feign.StorageApi;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class BusinessServiceImpl implements BusinessService {
    private final OrderApi orderApi;
    private final StorageApi storageApi;
    private final AccountApi accountApi;

    @Override
    @GlobalTransactional(rollbackFor = Exception.class)
    public void create(Long userId, Long productId, Integer count, Long money) {
        log.info("------->交易开始");

        //远程方法 生成订单
        log.info("------->生成订单开始");
        orderApi.create(userId, productId, count, money);
        log.info("------->生成订单结束");

        //远程方法 扣减库存
        log.info("------->扣减库存开始");
        storageApi.decrease(productId, count);
        log.info("------->扣减库存结束");

        //远程方法 扣减账户余额
        log.info("------->扣减账户开始");
        accountApi.decrease(userId, money);
        log.info("------->扣减账户结束");

        log.info("------->交易结束");
    }

    @Override
    public void update(Long userId, Long money, Integer status) {
        log.info("------->修改订单金额开始");
        orderApi.update(userId, money, status);
        log.info("------->修改订单金额结束");
    }
}
