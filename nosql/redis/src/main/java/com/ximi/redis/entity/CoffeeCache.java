package com.ximi.redis.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.joda.money.Money;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.math.BigInteger;

@RedisHash(value = "coffee")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CoffeeCache {

    private Long id;

    @Indexed
    private String name;

    private Money price;

}
