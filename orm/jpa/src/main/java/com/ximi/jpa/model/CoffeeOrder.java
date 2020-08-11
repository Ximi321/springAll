package com.ximi.jpa.model;

import lombok.*;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Builder
@Data
@Table(name = "t_coffee_order")
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CoffeeOrder extends BaseEntity implements Serializable {

    private String customer;

    @ManyToMany
    @JoinTable(name = "t_order_coffee")
    @OrderBy("id")
    private List<Coffee> items;

    @Enumerated
    @Column(nullable = false)
    private OrderStatus orderStatus;
}
