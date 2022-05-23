package com.camellibby.seata.client;

public interface BusinessService {
    /**
     * 采购
     */
    void purchase(String userId, String commodityCode, int orderCount);
}
