package com.ximi.train.customer.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.ximi.train.customer.model.Teacher;
import com.ximi.train.customer.service.TeacherService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("teacher")
public class TeacherController {

    @Resource
    private TeacherService teacherService;

    @HystrixCommand(fallbackMethod = "getDefaultByName")
    @GetMapping("/byName")
    public List<Teacher> getByName(@RequestParam("name") String name) {
        return teacherService.getByName(name);
    }

    public List<Teacher> getDefaultByName(String name){
        return new ArrayList<>();
    }

}
