package com.ximi.train.customer.controller;


import com.ximi.train.customer.model.Student;
import com.ximi.train.customer.service.StudentService;
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

    @GetMapping("/byName")
    public List<Student> getByName(@RequestParam("name") String name){
        return studentService.getByName(name);
    }

}
