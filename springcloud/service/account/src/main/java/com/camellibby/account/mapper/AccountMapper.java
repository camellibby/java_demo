package com.camellibby.account.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.camellibby.account.entity.Account;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AccountMapper extends BaseMapper<Account> {

    /**
     * 扣减账户余额
     *
     * @param userId 用户id
     * @param money  金额
     */
    void decrease(@Param("userId") Long userId, @Param("money") Long money);
}
