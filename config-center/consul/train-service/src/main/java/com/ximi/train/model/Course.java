package com.ximi.train.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "course")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Course extends BaseEntity {

    private String name;

    @ManyToOne
    private Teacher teacher;

    private Long price;

    @ManyToMany
    @JoinTable(name = "course_student")
    private List<Student> students;

}
