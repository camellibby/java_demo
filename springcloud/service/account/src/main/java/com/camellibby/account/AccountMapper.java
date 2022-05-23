package com.camellibby.account;

import feign.Param;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AccountMapper {

    /**
     * 扣减账户余额
     *
     * @param userId 用户id
     * @param money  金额
     */
    void decrease(@Param("userId") Long userId, @Param("money") Long money);
}
