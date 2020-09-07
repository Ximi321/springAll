package com.ximi.train.controller;

import com.ximi.train.model.Student;
import com.ximi.train.model.Teacher;
import com.ximi.train.service.StudentService;
import com.ximi.train.service.TeacherService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("teacher")
public class TeacherController {

    @Resource
    private TeacherService teacherService;

    @GetMapping("/byName")
    public List<Teacher> getByName(@RequestParam("name") String name){
        return teacherService.getByName(name);
    }
}
