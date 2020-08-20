package com.ximi.cache;

import com.ximi.cache.entity.User;
import com.ximi.cache.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@SpringBootApplication
@EnableCaching(proxyTargetClass = true)
@MapperScan(value = "com.ximi.cache.mapper")
public class CacheApplication implements CommandLineRunner {

	@Resource
	private IUserService userService;

	public static void main(String[] args) {
		SpringApplication.run(CacheApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		testCache();
	}

	private void testCache(){

		try {
			for(int index = 0;index < 5;index++){
				List<User> userList = userService.findByName("ximi");
				log.info("userlist " + index + "{}",userList);
			}
		} catch (Exception ex){
			log.error("",ex);
		}
	}
}
