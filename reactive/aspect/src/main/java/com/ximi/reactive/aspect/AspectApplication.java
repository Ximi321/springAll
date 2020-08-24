package com.ximi.reactive.aspect;

import com.ximi.reactive.aspect.model.Coffee;
import com.ximi.reactive.aspect.service.CoffeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import javax.annotation.Resource;

@SpringBootApplication
@EnableAspectJAutoProxy(proxyTargetClass = true)
@Slf4j
public class AspectApplication implements CommandLineRunner {

	@Resource
	private CoffeeService coffeeService;

	public static void main(String[] args) {
		SpringApplication.run(AspectApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		testAspect();
	}

	public void testAspect(){
		try {
			Coffee coffee = coffeeService.getByName("latte");
			log.info("coffee {}",coffee);
		} catch (Exception ex){
			log.error("",ex);
		}
	}
}
