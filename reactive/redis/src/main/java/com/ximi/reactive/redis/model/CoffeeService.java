package com.ximi.reactive.redis.model;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * reactiveRedisTemplate 简单的演示视例
 *
 * @author Ximi
 * @since 2020/08/23
 */
@Service
@Slf4j
public class CoffeeService {

    private String coffee_key = "coffee";

    @Resource
    private ReactiveRedisTemplate reactiveRedisTemplate;

    @Resource
    private JdbcTemplate jdbcTemplate;

    public Coffee getByName(String name) throws InterruptedException {

        List<Coffee> coffeeList = new ArrayList<>();
        CountDownLatch latch = new CountDownLatch(1);

        reactiveRedisTemplate.opsForHash()
                .get(coffee_key, name)
                .flatMap(value -> {
                    log.info("flatMap value {} {}", value, Thread.currentThread().getName());
                    return Mono.just(Coffee.builder().name(name).price((Long) value).build());
                })
                .defaultIfEmpty(getCoffeeFromMysql(name))
//                .subscribeOn(Schedulers.single())
                .doFinally(signalType -> {
                    log.info("do finish {} {}", signalType, Thread.currentThread().getName());
                    latch.countDown();
                })
                .subscribe(value -> {
                    log.info("subscribe value {} {}", value, Thread.currentThread().getName());
                    coffeeList.add((Coffee) value);
                }, e -> {
                    log.error("e", e);
                });
        latch.await();
        return coffeeList.size() > 0 ? coffeeList.get(0) : null;
    }

    private Coffee getCoffeeFromMysql(String name) {
        Coffee coffee = jdbcTemplate.queryForObject("select * from coffee where name = ?", new RowMapper<Coffee>() {
            @Override
            public Coffee mapRow(ResultSet rs, int rowNum) throws SQLException {
                Coffee coffee = new Coffee();
                coffee.setId(rs.getLong("id"));
                coffee.setName(rs.getString("name"));
                coffee.setPrice(rs.getLong("price"));
                return coffee;
            }
        }, name);

        if (coffee != null) {
            reactiveRedisTemplate.opsForHash()
                    .put(coffee_key, name, coffee.getPrice())
                    .subscribeOn(Schedulers.single())
                    .doFinally(signalType -> {
                        log.info("signalType-1 {} {}", signalType, Thread.currentThread().getName());
                    })
                    .subscribe();
        }
        return coffee;
    }
}
