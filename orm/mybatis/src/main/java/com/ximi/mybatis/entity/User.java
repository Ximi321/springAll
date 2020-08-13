package com.ximi.mybatis.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ximi.mybatis.handler.MoneyTypeHandler;
import lombok.*;
import lombok.experimental.Accessors;
import org.apache.ibatis.type.JdbcType;
import org.joda.money.Money;

/**
 * <p>
 * 
 * </p>
 *
 * @author Ximi
 * @since 2020-08-13
 */
@Data
@Builder
@TableName(autoResultMap = true)
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor   //需要指定一个无参构造函数,不然会出错
@AllArgsConstructor
public class User implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 主键ID
     */
    private Long id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 钱
     */
    @TableField(typeHandler = MoneyTypeHandler.class,jdbcType = JdbcType.BIGINT)
    private Money price;
}
