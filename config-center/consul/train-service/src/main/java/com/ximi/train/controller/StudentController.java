package com.ximi.train.controller;

import com.ximi.train.model.Student;
import com.ximi.train.service.StudentService;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("student")
public class StudentController {

    @Resource
    private StudentService studentService;

    @RateLimiter(name = "student")
    @GetMapping("/byName")
    public List<Student> getByName(@RequestParam("name") String name){
        return studentService.getByName(name);
    }

}
