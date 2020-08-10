package com.ximi.error;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Slf4j
public class PersonServiceImpl implements PersonService {

    @Resource
    private JdbcTemplate jdbcTemplate;

    @Override
    @Transactional
    public void insert(Person person){
        jdbcTemplate.update("insert into person(id,name,age) values (?,?,?)",
                person.getId(),
                person.getName(),
                person.getAge());

    }
}
