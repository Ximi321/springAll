package com.ximi.train.customer.controller;

import com.ximi.train.customer.model.Teacher;
import com.ximi.train.customer.service.TeacherService;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("teacher")
@Slf4j
public class TeacherController {

    @Resource
    private TeacherService teacherService;

    @Bulkhead(name = "teacher")
    @CircuitBreaker(name = "teacher", fallbackMethod = "fallbackByName")
    @GetMapping("/byName")
    public List<Teacher> getByName(@RequestParam("name") String name) {
        return teacherService.getByName(name);
    }

    /**
     * @param name
     * @param ex
     * @return
     */
    public List<Teacher> fallbackByName(String name, Exception ex) {
        log.info("fallbackByName " + ex.getMessage());
        return new ArrayList<>();
    }

}
