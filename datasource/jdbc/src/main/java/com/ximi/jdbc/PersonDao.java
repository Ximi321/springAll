package com.ximi.jdbc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Slf4j
@Repository
public class PersonDao {

    @Resource
    private JdbcTemplate jdbcTemplate;

    /**
     * 插入一条数据
     *
     * @param person
     */
    public void insert(Person person) {
        jdbcTemplate.update("insert into person(id,name,age) values (?,?,?)", person.id, person.name, person.age);
    }

    /**
     * 通过ID获取
     *
     * @param id
     * @return
     */
    public Person getPerson(int id) {
        try {
            return jdbcTemplate.queryForObject("select * from person where id = ?", new RowMapper<Person>() {
                @Override
                public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
                    Person person = new Person();
                    person.setId(rs.getInt("id"));
                    person.setName(rs.getString("name"));
                    person.setAge(rs.getInt("age"));
                    return person;
                }
            },id);
        } catch (Exception ex){
            log.error("x",ex);
        }
        return null;
    }

    /**
     * 批量插入
     *
     * @param list
     */
    public void batchInsert(List<Person> list) {
        jdbcTemplate.batchUpdate("insert into person(id,name,age) values (?,?,?)", new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setInt(1, list.get(i).getId());
                ps.setString(2, list.get(i).getName());
                ps.setInt(3, list.get(i).getAge());
            }

            @Override
            public int getBatchSize() {
                return list.size();
            }
        });
    }

    public List<Person> getList(){
        return jdbcTemplate.query("select * from person where 1 = 1", new RowMapper<Person>() {
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

}
