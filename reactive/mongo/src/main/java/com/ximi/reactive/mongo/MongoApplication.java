package com.ximi.reactive.mongo;

import com.ximi.reactive.mongo.model.Coffee;
import com.ximi.reactive.mongo.service.ReactiveCoffeeService;
import lombok.extern.slf4j.Slf4j;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CountDownLatch;

@Slf4j
@SpringBootApplication
public class MongoApplication implements CommandLineRunner {

	@Resource
	private ReactiveCoffeeService reactiveCoffeeService;

	public static void main(String[] args) {
		SpringApplication.run(MongoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		testReactiveCoffeeService();
	}

	private void testReactiveCoffeeService(){
		try {
			CountDownLatch cdl = new CountDownLatch(2);
			reactiveCoffeeService.insertAll(initCoffees(),()->{
//				log.info("complete {}",Thread.currentThread().getName());
				reactiveCoffeeService.update(cdl);
			},cdl);
			cdl.await();
		} catch (Exception ex){
			log.error("",ex);
		}
	}

	private List<Coffee> initCoffees(){
		Coffee espresso = Coffee.builder()
				.name("espresso")
				.price(Money.of(CurrencyUnit.of("CNY"), 20.0))
				.createTime(new Date())
				.updateTime(new Date())
				.build();
		Coffee latte = Coffee.builder()
				.name("latte")
				.price(Money.of(CurrencyUnit.of("CNY"), 30.0))
				.createTime(new Date())
				.updateTime(new Date())
				.build();

		return Arrays.asList(espresso, latte);
	}
}
