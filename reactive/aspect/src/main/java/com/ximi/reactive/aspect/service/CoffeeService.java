package com.ximi.reactive.aspect.service;

import com.ximi.reactive.aspect.model.Coffee;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;

@Service
public class CoffeeService {

    @Resource
    private JdbcTemplate jdbcTemplate;

    public Coffee getByName(String name) {
        return jdbcTemplate.queryForObject("select * from coffee where name = ?",
                new RowMapper<Coffee>() {
                    @Override
                    public Coffee mapRow(ResultSet rs, int rowNum) throws SQLException {
                        return Coffee.builder()
                                .name(rs.getString("name"))
                                .price(rs.getLong("price"))
                                .build();
                    }
                },name);
    }


}
