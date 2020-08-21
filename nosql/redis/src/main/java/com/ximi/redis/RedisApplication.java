package com.ximi.redis;

import com.ximi.redis.entity.Coffee;
import com.ximi.redis.service.ICoffeeService;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import java.util.List;

@SpringBootApplication
@Slf4j
@MapperScan(value = "com.ximi.redis.mapper")
public class RedisApplication implements CommandLineRunner {

	@Resource
	private RedisTemplate redisTemplate;

	@Resource
	private ICoffeeService iCoffeeService;

	public static void main(String[] args) {
		SpringApplication.run(RedisApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		testRedis();
		testRedisRepository();
	}

	private void testRedis(){
		try {
			redisTemplate.opsForValue().set("ximi","100");
			String value = (String) redisTemplate.opsForValue().get("ximi");
			log.info("value {}",value);
		} catch (Exception ex){
			log.error("",ex);
		}
	}

	private void testRedisRepository(){
		List<Coffee> coffeeList = iCoffeeService.findByName("latte");
		log.info("coffeelist :{}",coffeeList);
	}
}
