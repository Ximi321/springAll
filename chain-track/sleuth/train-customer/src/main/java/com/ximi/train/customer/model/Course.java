package com.ximi.train.customer.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Course {

    private long id;

    private String name;

    private Teacher teacher;

    private Long price;

    private List<Student> students;

    private Date updateTime;

    private Date createTime;

}
