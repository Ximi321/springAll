package com.ximi.mybatis;

import com.ximi.mybatis.entity.User;
import com.ximi.mybatis.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;
import java.util.List;

@SpringBootApplication
@Slf4j
@MapperScan(basePackages = "com.ximi.mybatis.mapper")
public class MybatisApplication implements CommandLineRunner {

    @Resource
    private IUserService userService;

    public static void main(String[] args) {
        SpringApplication.run(MybatisApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        testUser();
    }

    private void testUser() {
        try {
            User ximi = createUser("ximi");
            userService.save(ximi);
            log.info("user ximi {}", ximi);

            User yumi = createUser("yumi");
            userService.save(yumi);
            log.info("user yumi {}", yumi);

            User user = userService.getOneByName("ximi");
            log.info("user {}", user);

            List<User> userList = userService.list();
            log.info("userList {}", userList);

        } catch (Exception ex) {
            log.error("", ex);
        }
    }

    private User createUser(String name) {
        User user = User.builder()
                .name(name)
                .age(100)
                .email("6666")
                .price(Money.of(CurrencyUnit.of("CNY"), 100))
                .build();

        return user;
    }

}
