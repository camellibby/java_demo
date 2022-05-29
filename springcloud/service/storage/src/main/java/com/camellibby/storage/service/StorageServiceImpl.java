package com.camellibby.storage.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.camellibby.storage.entity.Storage;
import com.camellibby.storage.mapper.StorageMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class StorageServiceImpl extends ServiceImpl<StorageMapper, Storage> implements StorageService {
    /**
     * 扣减库存
     *
     * @param productId 产品id
     * @param count     数量
     * @return
     */
    @Override
    public void decrease(Long productId, Integer count) {
        Storage storage = baseMapper.selectOne(Wrappers.lambdaQuery(Storage.class)
                .eq(Storage::getProductId, productId));
        if (storage.getTotal() < count) {
            throw new IllegalArgumentException("库存不足");
        }
        log.info("------->扣减库存开始");
        baseMapper.decrease(productId, count);
        log.info("------->扣减库存结束");
    }
}
