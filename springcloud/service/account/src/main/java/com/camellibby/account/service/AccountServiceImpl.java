package com.camellibby.account.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.camellibby.account.entity.Account;
import com.camellibby.account.feign.BusinessApi;
import com.camellibby.account.mapper.AccountMapper;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account> implements AccountService {
    private BusinessApi businessApi;

    @Override
    public void decrease(Long userId, Long money) {
        Account account = baseMapper.selectOne(Wrappers.lambdaQuery(Account.class)
                .eq(Account::getUserId, userId));
        if (account.getResidue() < money) {
            throw new IllegalArgumentException("账户余额不足");
        }
        log.info("------->扣减账户开始account中");
        baseMapper.decrease(userId, money);
        log.info("------->扣减账户结束account中");

        //修改订单状态，此调用会导致调用成环
        log.info("修改订单状态开始");
        String mes = businessApi.update(userId, (money * 9) / 100, 0);
        log.info("修改订单状态结束：{}", mes);
    }
}
