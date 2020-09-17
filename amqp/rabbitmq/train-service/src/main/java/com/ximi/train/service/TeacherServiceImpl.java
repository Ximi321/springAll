package com.ximi.train.service;

import com.ximi.train.model.Student;
import com.ximi.train.model.Teacher;
import com.ximi.train.repository.StudentRepository;
import com.ximi.train.repository.TeacherRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {

    @Resource
    private TeacherRepository teacherRepository ;

    @Override
    public List<Teacher> getByName(String name) {
        return teacherRepository.getByName(name);
    }
}
