package com.ximi.jedis;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * jedis 客户端的配置
 *
 * @author Ximi
 * @since 2020/08/20
 */
@Configuration
public class JedisConfiguration {

    @Bean
    @ConfigurationProperties("jedis")
    public JedisPoolConfig jedisPoolConfig() {
        return new JedisPoolConfig();
    }

    @Bean(destroyMethod = "close")
    public JedisPool jedisPool(JedisPoolConfig jedisPoolConfig, @Value("${jedis.host}") String host) {
        return new JedisPool(jedisPoolConfig, host);
    }

}
