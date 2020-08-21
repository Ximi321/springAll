package com.ximi.redis.service;

import com.ximi.redis.entity.Coffee;
import com.ximi.redis.entity.CoffeeCache;
import com.ximi.redis.mapper.CoffeeMapper;
import com.ximi.redis.repository.CoffeeCacheRepository;
import com.ximi.redis.service.ICoffeeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 咖啡 服务实现类
 * </p>
 *
 * @author Ximi
 * @since 2020-08-21
 */
@Service
public class CoffeeServiceImpl extends ServiceImpl<CoffeeMapper, Coffee> implements ICoffeeService {

    @Resource
    private CoffeeMapper coffeeMapper;

    @Resource
    private CoffeeCacheRepository coffeeCacheRepository;

    @Override
    public List<Coffee> findByName(String name) {

        List<CoffeeCache> coffeeCaches =  coffeeCacheRepository.findByName(name);
        if(!CollectionUtils.isEmpty(coffeeCaches)){
            List<Coffee> coffees = new ArrayList<>();
            for(CoffeeCache coffeeCache:coffeeCaches){
                coffees.add(Coffee.builder().name(coffeeCache.getName())
                            .price(coffeeCache.getPrice())
                            .build());
            }
            return coffees;
        }else {
            List<Coffee> coffees = coffeeMapper.findByName(name);
            if(!CollectionUtils.isEmpty(coffees)){
                for(Coffee coffee:coffees){
                    coffeeCaches.add(CoffeeCache.builder().id(coffee.getId())
                            .name(coffee.getName())
                            .price(coffee.getPrice())
                            .build());
                }
                coffeeCacheRepository.saveAll(coffeeCaches);
            }
            return coffees;
        }
    }
}
