package com.ximi.multidatasource;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.annotation.Resource;
import javax.sql.DataSource;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class,
                DataSourceTransactionManagerAutoConfiguration.class,
                JdbcTemplateAutoConfiguration.class
        }
)
@Slf4j
public class MultiDatasourceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MultiDatasourceApplication.class, args);
    }

    @Bean
    @ConfigurationProperties(value = "foo.datasource")
    public DataSourceProperties fooDataSourceProperties(){
        return new DataSourceProperties();
    }

    @Bean
    @Resource
    public DataSource fooDataSource(DataSourceProperties fooDataSourceProperties){
        log.info("foo datasource:{}",fooDataSourceProperties.getUrl());
        return fooDataSourceProperties.initializeDataSourceBuilder().build();
    }

    @Bean
    @Resource
    public PlatformTransactionManager fooTransactionManager(DataSource fooDataSource){
        return new DataSourceTransactionManager(fooDataSource);
    }

    @Bean
    @ConfigurationProperties(value = "bar.datasource")
    public DataSourceProperties barDataSourceProperties(){
        return new DataSourceProperties();
    }

    @Bean
    @Resource
    public DataSource barDataSource(DataSourceProperties barDataSourceProperties){
        log.info("bar datasource:{}",barDataSourceProperties.getUrl());
        return barDataSourceProperties.initializeDataSourceBuilder().build();
    }

    @Bean
    @Resource
    public PlatformTransactionManager barTransactionManager(DataSource barDataSource){
        return new DataSourceTransactionManager(barDataSource);
    }

}
