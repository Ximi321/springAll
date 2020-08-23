package com.ximi.reactive;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@SpringBootApplication
@Slf4j
public class ReactiveApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(ReactiveApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
		testFlux();
//        textMono();
    }

    public void testFlux() throws InterruptedException {
        Flux.range(1, 10)
                .doOnRequest(n -> { log.info("request {} number {}", n, Thread.currentThread().getName()); })
                .doOnComplete(() -> { log.info("complete one {}", Thread.currentThread().getName()); })
                .subscribeOn(Schedulers.newSingle("new single thread"))
                .publishOn(Schedulers.elastic())
                .filter(i -> {
                    if (i % 2 == 0) { return true; }
                    return false;
                })
                .map(i -> {
                    log.info("publish {},{}", Thread.currentThread().getName(), i);
                    return i;
//					return 10 / (i -3 );
                })
//				.subscribeOn(Schedulers.single())
//				.onErrorResume(e ->{
//					log.error("exception",e);
//					return Mono.just(-1);
//				})
                .doOnComplete(() -> { log.info("complete two {}", Thread.currentThread().getName()); })
                .subscribe(i -> { log.info("subscribe {} {}", Thread.currentThread().getName(), i); },
                        e -> { log.error("subscribe ", e); },
                        () -> { log.info("subscribe complete {}", Thread.currentThread().getName()); }
//						,s -> s.request(5)
                );

        Thread.sleep(2000);

    }


    public void textMono() throws InterruptedException {
        Mono.just(5)
                .doOnRequest(n -> { log.info("request number {},{}", n, Thread.currentThread().getName()); })
                .subscribeOn(Schedulers.single())
                .publishOn(Schedulers.elastic())
                .map(i -> {
                    log.info("publisher {} {}", i, Thread.currentThread().getName());
                    return i * i;
                })
                .filter(i -> {
                    if (i % 5 == 0) return true;
                    return false;
                })
                .subscribe(i -> { log.info("subscribe {} {}", i, Thread.currentThread().getName()); });

        Thread.sleep(2000);
    }
}
