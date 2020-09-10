package com.ximi.train.service;

import com.ximi.train.model.Student;
import com.ximi.train.repository.StudentRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Resource
    private StudentRepository studentRepository;

    @Override
    public List<Student> getByName(String name) {
        return studentRepository.getByName(name);
    }
}
