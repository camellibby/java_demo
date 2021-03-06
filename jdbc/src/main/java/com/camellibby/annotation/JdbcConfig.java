package com.camellibby.annotation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.transaction.TransactionManager;

import javax.sql.DataSource;

/**
 * @author luoxinliang
 */
@Configuration
public class JdbcConfig {
    @Bean
    public DataSource getDataSource(){
         SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
         dataSource.setUrl("jdbc:mysql://localhost:3306/test?useUnicode=true");
         dataSource.setDriverClass(com.mysql.cj.jdbc.Driver.class);
         dataSource.setUsername("root");
         dataSource.setPassword("123456");

//        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
//        dataSource.setUrl("jdbc:h2:./jdbc/test");
//        dataSource.setDriverClass(org.h2.Driver.class);

        return dataSource;
    }

    @Bean
    public JdbcTemplate getJdbcTemplate(){
        DataSource dataSource = getDataSource();
        return new JdbcTemplate(dataSource);
    }

    @Bean
    public TransactionManager getTransactionManager(DataSource dataSource) {
        TransactionManager transactionManager = new DataSourceTransactionManager(dataSource);
        return transactionManager;
    }
}
