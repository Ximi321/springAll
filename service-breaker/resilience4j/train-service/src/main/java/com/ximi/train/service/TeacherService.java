package com.ximi.train.service;

import com.ximi.train.model.Teacher;

import java.util.List;

public interface TeacherService {

    public List<Teacher> getByName(String name);

}
