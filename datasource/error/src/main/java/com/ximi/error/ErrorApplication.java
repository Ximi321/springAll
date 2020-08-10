package com.ximi.error;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;

@SpringBootApplication
@Slf4j
public class ErrorApplication implements CommandLineRunner {

	@Resource
	private PersonService personService;

	public static void main(String[] args) {
		SpringApplication.run(ErrorApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		try {
			personService.insert(createPerson(1));
			personService.insert(createPerson(1));
		} catch (Exception ex){
			log.error("text error " + ex.getClass(),ex);
		}
	}


	private Person createPerson(Integer id){
		Person person = new Person();
		person.setId(id);
		person.setName("ximi" + id);
		person.setAge(id + 10);
		return person;
	}

}
