package com.camellibby.storage;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class StorageServiceImpl implements StorageService {
    private StorageMapper storageMapper;
    /**
     * 扣减库存
     *
     * @param productId 产品id
     * @param count     数量
     * @return
     */
    @Override
    public void decrease(Long productId, Integer count) {
        log.info("------->扣减库存开始");
        storageMapper.decrease(productId, count);
        log.info("------->扣减库存结束");
    }
}
