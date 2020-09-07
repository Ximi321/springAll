package com.ximi.train;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class TrainServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrainServerApplication.class, args);
	}

}
