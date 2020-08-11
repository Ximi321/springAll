package com.ximi.jpa.model;

import lombok.*;
import org.hibernate.annotations.Type;
import org.joda.money.Money;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "t_menu")
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Coffee extends BaseEntity{

    private String name;

    @Type(type = "org.jadira.usertype.moneyandcurrency.joda.PersistentMoneyAmount",
            parameters = {@org.hibernate.annotations.Parameter(name = "currencyCode", value = "CNY")})
    private Money price;

}
