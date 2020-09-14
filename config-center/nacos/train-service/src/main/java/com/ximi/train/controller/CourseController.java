package com.ximi.train.controller;

import com.ximi.train.model.Course;
import com.ximi.train.repository.CourseRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("course")
public class CourseController {

    @Resource
    private CourseRepository courseRepository;

    @GetMapping("all")
    public List<Course> getAll(){
        return courseRepository.findAll();
    }

}
