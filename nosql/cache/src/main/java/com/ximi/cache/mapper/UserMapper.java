package com.ximi.cache.mapper;

import com.ximi.cache.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Ximi
 * @since 2020-08-20
 */
public interface UserMapper extends BaseMapper<User> {

    public List<User> findByName(@Param("name") String name);

}
