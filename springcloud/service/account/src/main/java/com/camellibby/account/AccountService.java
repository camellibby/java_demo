package com.camellibby.account;

public interface AccountService {
    /**
     * 扣减账户余额
     *
     * @param userId 用户id
     * @param money  金额
     */
    void decrease(Long userId, Long money);
}
