package com.camellibby.account.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.camellibby.account.entity.Account;

public interface AccountService extends IService<Account> {
    /**
     * 扣减账户余额
     *
     * @param userId 用户id
     * @param money  金额
     */
    void decrease(Long userId, Long money);
}
