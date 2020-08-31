package com.ximi.train.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@Table(name = "student")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Student extends BaseEntity{

    private String name;

    private String age;

}
