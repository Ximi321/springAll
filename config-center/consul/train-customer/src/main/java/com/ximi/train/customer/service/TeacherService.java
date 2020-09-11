package com.ximi.train.customer.service;

import com.ximi.train.customer.model.Teacher;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "train-service",
        contextId = "teacher",
        path = "teacher")
public interface TeacherService {

    /**
     *
     * @param name
     * @return
     */
//    @Retry(name = "teacher")
    @GetMapping(path = "byName")
    public List<Teacher> getByName(@RequestParam("name") String name);

}
