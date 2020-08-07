package com.ximi.jdbc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.*;

/**
 * 其他jdbcTemplate 操作数据库
 *
 * @author Ximi
 * @since 2020/08/07
 */
@Slf4j
@Repository
public class OtherJdbcPersonDao {

    @Resource
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Resource
    private SimpleJdbcInsert simpleJdbcInsert;

    public void batchInsert(List<Person> list){
        namedParameterJdbcTemplate.batchUpdate("insert into person(id,name,age) values(:id,:name,:age)",
                SqlParameterSourceUtils.createBatch(list));
    }

    public List<Person> getListByName(String name){
        Map<String,Object> map = new HashMap<>();
        map.put("name",name);
        return namedParameterJdbcTemplate.query("select * from person where name = :name", map, new RowMapper<Person>() {
            @Override
            public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
                Person person = new Person();
                person.setId(rs.getInt("id"));
                person.setName(rs.getString("name"));
                person.setAge(rs.getInt("age"));
                return person;
            }
        });
    }

    public void insert(Person person){
        simpleJdbcInsert
                .usingGeneratedKeyColumns("id")
                .withTableName("person")
                .execute(SqlParameterSourceUtils.createBatch(person)[0]);
    }

}
