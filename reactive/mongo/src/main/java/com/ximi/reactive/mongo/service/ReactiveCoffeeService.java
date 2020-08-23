package com.ximi.reactive.mongo.service;

import com.ximi.reactive.mongo.model.Coffee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import reactor.core.scheduler.Schedulers;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.CountDownLatch;

@Service
@Slf4j
public class ReactiveCoffeeService {

    @Resource
    private ReactiveMongoTemplate mongoTemplate;

    /**
     * 全部插入进去
     *
     * @param list
     * @param completeRunnable
     */
    public void insertAll(List<Coffee> list, Runnable completeRunnable, CountDownLatch cdl) {
        mongoTemplate.insertAll(list)
                .publishOn(Schedulers.elastic())
                .doOnNext(coffee -> {
                    log.info("next coffee {} {}", coffee, Thread.currentThread().getName());
                })
                .doOnComplete(completeRunnable)
                .doFinally(signalType -> {
                    log.info("doFinally insertAll {} {}", signalType, Thread.currentThread().getName());
                    cdl.countDown();
                })
                .count()
                .subscribe(
                        c -> {
                            log.info("insert coffee count {} {}", c, Thread.currentThread().getName());
                        },
                        e -> {
                            log.error("", e);
                        });
    }

    public void update(CountDownLatch cdl) {
        mongoTemplate.updateMulti(Query.query(Criteria.where("price").is(2500L)),
                new Update().inc("price", -500L).currentDate("updateTime"),
                Coffee.class)
                .doFinally(signalType -> {
                    cdl.countDown();
                    log.info("doFinally update{} {}", signalType, Thread.currentThread().getName());
                })
                .subscribe(c -> {
                    log.info("subscribe c {} {}", c, Thread.currentThread().getName());
                });

    }
}
