package com.camellibby.seata.client;

import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
@Slf4j
public class BusinessServiceImpl implements BusinessService {

    private final StorageClient storageClient;

    private final OrderClient orderClient;

    /**
     * 采购
     */
    @GlobalTransactional
    public void purchase(String userId, String commodityCode, int orderCount) {
        log.info("purchase begin ... xid: " + RootContext.getXID());
        storageClient.deduct(commodityCode, orderCount);
        orderClient.create(userId, commodityCode, orderCount);
    }
}
