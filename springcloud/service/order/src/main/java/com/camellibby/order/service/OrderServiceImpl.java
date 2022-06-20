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

    /**
     * 创建订单
     *
     * @param userId
     * @param productId
     * @param count
     * @param money
     * @return 测试结果：
     * 1.添加本地事务：仅仅扣减库存
     * 2.不添加本地事务：创建订单，扣减库存
     */
    @Override
    @GlobalTransactional(rollbackFor = Exception.class)
    public void create(Long userId, Long productId, Integer count, Long money) {
        log.info("------->交易开始");

        //远程方法 生成订单
        log.info("------->生成订单开始");
        orderMapper.create(userId, productId, count, money);
        log.info("------->生成订单结束");
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
