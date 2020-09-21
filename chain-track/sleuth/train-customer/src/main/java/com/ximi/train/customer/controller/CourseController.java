package com.ximi.train.customer.controller;

import com.ximi.train.customer.integration.CourseMessageSend;
import com.ximi.train.customer.model.Course;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("course")
@Slf4j
public class CourseController {

    @Resource
    private CourseMessageSend courseMessageSend;

    @PostMapping("create")
    public void createCourse(@RequestBody Course course) {
        try {
            courseMessageSend.save(course);
        } catch (Exception ex) {
            log.error("create course exception", ex);
        }
    }

}
