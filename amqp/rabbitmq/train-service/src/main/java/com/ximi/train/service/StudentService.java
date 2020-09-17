package com.ximi.train.service;

import com.ximi.train.model.Student;

import java.util.List;

public interface StudentService {

    public List<Student> getByName(String name);

}
