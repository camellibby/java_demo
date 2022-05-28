package com.camellibby.account.entity;

import lombok.Data;

@Data
public class Account {

    private Long id;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 总额度
     */
    private Long total;

    /**
     * 已用额度
     */
    private Long used;

    /**
     * 剩余额度
     */
    private Long residue;
}

