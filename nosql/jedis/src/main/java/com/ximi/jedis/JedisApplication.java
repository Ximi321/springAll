package com.ximi.jedis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.annotation.Resource;

@SpringBootApplication
@Slf4j
public class JedisApplication implements CommandLineRunner {

    @Resource
    private JedisPool jedisPool;

    public static void main(String[] args) {
        SpringApplication.run(JedisApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        testJedis();
    }

    private void testJedis() {
        try {
            Jedis jedis = jedisPool.getResource();
            jedis.set("ximi", "199");
            String value = jedis.get("ximi");
            log.info("value: {}", value);
        } catch (Exception ex) {
            log.error("", ex);
        }
    }
}
