package com.ximi.transcation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@SpringBootApplication
@Slf4j
public class TranscationApplication implements CommandLineRunner {

	@Resource
	private PersonService personService;

	@Resource
	private ProgrammaticTransactionPersonDao programmaticTransactionPersonDao;

	public static void main(String[] args) {
		SpringApplication.run(TranscationApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		testInsertPerson();
		testInsertPersonThenRollBack();
		testInvokeInsertPersonThenRollBack();
		testProgrammaticInsert();
		testProgrammaticInsertThenRollBcak();
	}

	public void testInsertPerson(){
		Person person = createPerson(1);
		try {
			personService.insert(person);
		} finally {
			log.info("person count " + personService.getCount());
		}
	}

	public void testInsertPersonThenRollBack(){
		Person person = createPerson(2);
		try {
			personService.insertThenRollback(person);
		} catch (Exception ex){
			log.info("person count " + personService.getCount());
		}
	}

	public void testInvokeInsertPersonThenRollBack(){
		Person person = createPerson(3);
		try {
			personService.invokeInsertThenRollback(person);
		} catch (Exception ex){
			log.info("person count " + personService.getCount());
		}
	}

	public void testProgrammaticInsert(){
		Person person = createPerson(4);
		try {
			programmaticTransactionPersonDao.insert(person);
		} finally {
			log.info("person count " + programmaticTransactionPersonDao.getCount());
		}
	}

	public void testProgrammaticInsertThenRollBcak(){
		Person person = createPerson(5);
		try {
			programmaticTransactionPersonDao.insertThenRollBack(person);
		} finally {
			log.info("person count " + programmaticTransactionPersonDao.getCount());
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
