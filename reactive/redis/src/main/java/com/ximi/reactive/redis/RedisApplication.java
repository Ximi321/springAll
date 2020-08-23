package com.ximi.reactive.redis;

import com.ximi.reactive.redis.model.Coffee;
import com.ximi.reactive.redis.service.CoffeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;

@SpringBootApplication
@Slf4j
public class RedisApplication implements CommandLineRunner {

	@Resource
	private CoffeeService coffeeService;


	public static void main(String[] args) {
		SpringApplication.run(RedisApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		try {
			Coffee coffee = coffeeService.getByName("latte");
			log.info("coffeeService {}",coffee);
		} catch (Exception ex){
			log.error("",ex);
		}
	}


}
