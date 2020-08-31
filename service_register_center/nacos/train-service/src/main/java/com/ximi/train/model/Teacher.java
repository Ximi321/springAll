package com.ximi.train.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "teacher")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Teacher extends BaseEntity{

    public String name;

    public String age;

}
