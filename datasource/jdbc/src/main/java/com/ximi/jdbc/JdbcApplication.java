package com.ximi.jdbc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@SpringBootApplication
public class JdbcApplication implements CommandLineRunner{

	@Resource
	private PersonDao personDao;

	@Resource
	private OtherJdbcPersonDao otherJdbcPersonDao;

	public static void main(String[] args) {
		SpringApplication.run(JdbcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		testPersonDao();
		testOtherPersonDao();
	}

	private void testPersonDao(){
		personDao.insert(createPerson(1));
		log.info(personDao.getPerson(1).toString());

		List<Person> list = new ArrayList<>();
		list.add(createPerson(2));
		list.add(createPerson(3));

		personDao.batchInsert(list);
		log.info(personDao.getList().toString());
	}

	private void testOtherPersonDao(){

		List<Person> list = new ArrayList<>();
		list.add(createPerson(2));
		list.add(createPerson(3));

		otherJdbcPersonDao.batchInsert(list);
		log.info(otherJdbcPersonDao.getListByName("ximi2").toString());

		otherJdbcPersonDao.insert(createPerson(4));
		log.info(otherJdbcPersonDao.getListByName("ximi4").toString());
	}


	private Person createPerson(Integer id){
		Person person = new Person();
		person.setId(id);
		person.setName("ximi" + id);
		person.setAge(id + 10);
		return person;
	}
}
