package com.ximi.druid;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * druid 防火墙
 *
 * @author Ximi
 * @since 2020/08/06
 */
@Slf4j
@RestController
public class DruidWebStatController {

    @Resource
    private JdbcTemplate jdbcTemplate;

    @RequestMapping(value = "query")
    public Object query(){
        try {
            jdbcTemplate.queryForList("select * from person where id = 1");
            jdbcTemplate.queryForList("select * from animal where id = 1");
        } catch (Exception ex){
            log.error("异常",ex);
            return false;
        }
        return true;
    }
}
