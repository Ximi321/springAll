package com.ximi.train.customer.service;

import com.ximi.train.customer.model.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class StudentFallBackService implements StudentService {

    @Override
    public List<Student> getByName(String name) {
        log.error("teacher getByName fail");
        return null;
    }
}
