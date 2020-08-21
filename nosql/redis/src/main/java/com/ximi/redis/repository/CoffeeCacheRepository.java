package com.ximi.redis.repository;

import com.ximi.redis.entity.CoffeeCache;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface CoffeeCacheRepository extends CrudRepository<CoffeeCache,Long> {

    public List<CoffeeCache> findByName(String name);

}
