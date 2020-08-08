package com.ximi.transcation;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class PersonServiceImpl implements PersonService {

    @Resource
    private JdbcTemplate jdbcTemplate;

    @Override
    @Transactional
    public void insert(Person person) {
        jdbcTemplate.update("insert into person(id,name,age) values (?,?,?)",
                person.id, person.name, person.age);
    }

    @Override
    @Transactional(rollbackFor = RollBackException.class)
    public void insertThenRollback(Person person) throws RollBackException {
        jdbcTemplate.update("insert into person(id,name,age) values (?,?,?)",
                person.id, person.name, person.age);
        throw new RollBackException();
    }

    @Override
    public void invokeInsertThenRollback(Person person) throws RollBackException {
        insertThenRollback(person);
    }

    @Override
    public Integer getCount() {
        return jdbcTemplate.queryForObject("select count(1) from person",Integer.class);
    }
}
