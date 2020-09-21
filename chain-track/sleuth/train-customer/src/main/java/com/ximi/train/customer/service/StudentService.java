package com.ximi.train.customer.service;

import com.ximi.train.customer.model.Student;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "train-service",
        contextId = "student",
        path = "student",
        fallback = StudentFallBackService.class,
        qualifier = "studentService")
public interface StudentService {

    @GetMapping(path = "byName")
    public List<Student> getByName(@RequestParam("name") String name);

}
