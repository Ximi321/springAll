package com.ximi.train.repository;

import com.ximi.train.model.Student;
import com.ximi.train.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher,Long> {

    public List<Teacher> getByName(String name);

}
