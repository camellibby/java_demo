package com.camellibby.order.service;

import com.camellibby.order.feign.AccountApi;
import com.camellibby.order.feign.StorageApi;
import com.camellibby.order.entity.Order;
import com.camellibby.order.mapper.OrderMapper;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {
    private final OrderMapper orderMapper;
    private final StorageApi storageApi;
    private final AccountApi accountApi;

    /**
     * 创建订单
     *
     * @param order
     * @return 测试结果：
     * 1.添加本地事务：仅仅扣减库存
     * 2.不添加本地事务：创建订单，扣减库存
     */
    @Override
    @GlobalTransactional(rollbackFor = Exception.class)
    public void create(Order order) {
        log.info("------->交易开始");
        //本地方法
        orderMapper.create(order);

        //远程方法 扣减库存
        storageApi.decrease(order.getProductId(), order.getCount());

        //远程方法 扣减账户余额

        log.info("------->扣减账户开始order中");
        accountApi.decrease(order.getUserId(), order.getMoney());
        log.info("------->扣减账户结束order中");

        log.info("------->交易结束");
    }

    /**
     * 修改订单状态
     */
    @Override
    public void update(Long userId, Long money, Integer status) {
        log.info("修改订单状态，入参为：userId={},money={},status={}", userId, money, status);
        orderMapper.update(userId, money, status);
    }
}
