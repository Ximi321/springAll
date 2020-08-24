package com.ximi.reactive.aspect.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Coffee {

    private long id;

    private String name;

    private Long price;

    private Date updateTime;

    private Date createTime;

}
