package com.ximi.jpa;

import com.ximi.jpa.model.Coffee;
import com.ximi.jpa.model.CoffeeOrder;
import com.ximi.jpa.model.OrderStatus;
import com.ximi.jpa.repository.CoffeeOrderRepository;
import com.ximi.jpa.repository.CoffeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.Arrays;

@SpringBootApplication
@EnableJpaRepositories
@EnableTransactionManagement
@Slf4j
public class JpaApplication implements CommandLineRunner {

    @Resource
    private CoffeeRepository coffeeRepository;

    @Resource
    private CoffeeOrderRepository coffeeOrderRepository;


    public static void main(String[] args) {
        SpringApplication.run(JpaApplication.class, args);
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        intiOrder();
        queryOrder();
    }

    private void intiOrder() {

        Coffee latte = Coffee.builder()
                .name("latte")
                .price(Money.of(CurrencyUnit.of("CNY"), 10))
                .build();

        coffeeRepository.save(latte);
        log.info("Coffee:{}", latte);

        Coffee nescafe = Coffee.builder()
                .name("nescafe")
                .price(Money.of(CurrencyUnit.of("CNY"), 30))
                .build();

        coffeeRepository.save(nescafe);
        log.info("Coffee:{}", nescafe);

        CoffeeOrder firstCoffeeOrder = CoffeeOrder.builder()
                .orderStatus(OrderStatus.INIT)
                .customer("Ximi")
                .items(Arrays.asList(latte, nescafe))
                .build();

        coffeeOrderRepository.save(firstCoffeeOrder);
        log.info("Coffee Order:{}", firstCoffeeOrder);

        CoffeeOrder secondCoffeeOrder = CoffeeOrder.builder()
                .orderStatus(OrderStatus.INIT)
                .customer("Ximi")
                .items(Arrays.asList(nescafe))
                .build();

        coffeeOrderRepository.save(secondCoffeeOrder);
        log.info("Coffee Order:{}", secondCoffeeOrder);

    }


    private void queryOrder() {

        coffeeRepository.findAll(Sort.by(Sort.Direction.DESC, "id"))
                .forEach(coffee -> {
                    log.info("coffee :{}", coffee);
                });

        coffeeOrderRepository.findTop3ByOrderByUpdateTimeDescIdAsc()
                .forEach(coffeeOrder -> {
                    log.info("coffeeOrder:{}", coffeeOrder);
                });

        coffeeOrderRepository.findByCustomerOrderById("Ximi")
                .forEach(coffeeOrder -> {
                    log.info("coffeeOrder:{}", coffeeOrder);
                });

        coffeeOrderRepository.findByItems_Name("nescafe")
                .forEach(coffeeOrder -> {
                    log.info("coffeeOrder:{}",coffeeOrder);
                });

    }
}
