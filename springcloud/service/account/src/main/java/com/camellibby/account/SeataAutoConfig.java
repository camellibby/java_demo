package com.camellibby.account;

import io.seata.spring.annotation.GlobalTransactionScanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SeataAutoConfig {
    private final static Logger logger = LoggerFactory.getLogger(SeataAutoConfig.class);

    /**
     * init global transaction scanner
     *
     * @Return: GlobalTransactionScanner
     */
    @Bean
    public GlobalTransactionScanner globalTransactionScanner() {
        logger.info("配置seata........");
        return new GlobalTransactionScanner("account", "seata-service-group");
    }
}
