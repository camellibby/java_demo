package com.camellibby.account;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountMapper accountMapper;
    private final OrderApi orderApi;

    @Override
    public void decrease(Long userId, Long money) {
        //模拟超时异常，全局事务回滚
        //        try {
        //            Thread.sleep(30*1000);
        //        } catch (InterruptedException e) {
        //            e.printStackTrace();
        //        }
        accountMapper.decrease(userId, money);
        log.info("------->扣减账户结束account中");

        //修改订单状态，此调用会导致调用成环
        log.info("修改订单状态开始");
        String mes = orderApi.update(userId, money * 9, 0);
        log.info("修改订单状态结束：{}", mes);
    }
}
