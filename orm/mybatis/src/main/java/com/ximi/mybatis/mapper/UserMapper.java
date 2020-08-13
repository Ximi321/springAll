package com.ximi.mybatis.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ximi.mybatis.entity.User;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author Ximi
 * @since 2020-08-13
 */
public interface UserMapper extends BaseMapper<User> {

    /**
     * 根据用户名获取一条数据
     *
     * @param name
     * @return
     */
    public User getOneByName(@Param("name") String name);

}
