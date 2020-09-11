package com.ximi.train.controller;

import com.ximi.train.model.Teacher;
import com.ximi.train.service.TeacherService;
import io.github.resilience4j.ratelimiter.RateLimiter;
import io.github.resilience4j.ratelimiter.RateLimiterRegistry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("teacher")
@Slf4j
public class TeacherController {

    @Resource
    private TeacherService teacherService;

    private RateLimiter rateLimiter;

    public TeacherController(RateLimiterRegistry rateLimiterRegistry) {
        rateLimiter = rateLimiterRegistry.rateLimiter("teacher");
    }

    @GetMapping("/byName")
    public List<Teacher> getByName(@RequestParam("name") String name) {
        List<Teacher> teachers = rateLimiter.executeSupplier(1,() -> teacherService.getByName(name));
        return teachers;
    }
}
