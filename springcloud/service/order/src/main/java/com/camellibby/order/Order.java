package com.camellibby.order;

import lombok.Data;

@Data
public class Order {

    private Long id;

    private Long userId;

    private Long productId;

    private Integer count;

    private Long money;

    /**
     * 订单状态：0：创建中；1：已完结
     */
    private Integer status;

}
