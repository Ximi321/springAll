package com.ximi.mongo;

import com.mongodb.client.result.UpdateResult;
import com.ximi.mongo.conversions.MoneyConversions;
import com.ximi.mongo.model.User;
import com.ximi.mongo.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@SpringBootApplication
@Slf4j
@EnableMongoRepositories
public class MongoApplication implements CommandLineRunner {

    @Resource
    private MongoTemplate mongoTemplate;

    @Resource
    private UserRepository userRepository;

    /**
     * 自定义一个转化 bean
     *
     * @return
     */
    @Bean
    MongoCustomConversions mongoCustomConversions() {
        MoneyConversions moneyConversions = new MoneyConversions();
        return new MongoCustomConversions(Arrays.asList(moneyConversions));
    }

    public static void main(String[] args) {
        SpringApplication.run(MongoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//        testMongoTemplate();
        testUserRepository();
    }

    private void testMongoTemplate() {

        try {
            User ximi = mongoTemplate.insert(createUser("ximi"));
            User yumi = mongoTemplate.insert(createUser("yumi"));

            List<User> userList = mongoTemplate.findAll(User.class);
            log.info("userList {}", userList);

            List<User> ximiList = mongoTemplate.find(Query.query(Criteria.where("name").is("ximi")), User.class);
            log.info("ximiList {}", ximiList);

            UpdateResult result = mongoTemplate.updateMulti(Query.query(Criteria.where("name").is("ximi")),
                    new Update().currentDate("updateTime")
                            .set("age", 101)
                    , User.class);

            log.info("Update result {}", result.getModifiedCount());
            ximi = mongoTemplate.findById(ximi.getId(), User.class);
            log.info("Update ximi {}", ximi);

        } catch (Exception ex) {
            log.error("", ex);
        }
    }

    private void testUserRepository() {

        try {
            User youxi = createUser("youxi");
            userRepository.insert(youxi);
            log.info("youxi {}", youxi);

            List<User> youxilist = userRepository.findByName("youxi");
            log.info("youxilist {}", youxilist);
        } catch (Exception ex) {
            log.error("", ex);
        }
    }

    private User createUser(String name) {
        return User.builder()
                .name(name)
                .age(100)
                .updateTime(new Date())
                .createTime(new Date())
                .money(Money.of(CurrencyUnit.of("CNY"),100))
                .build();
    }
}
