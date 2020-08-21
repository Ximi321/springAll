package com.ximi.redis.mapper;

import com.ximi.redis.entity.Coffee;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 咖啡 Mapper 接口
 * </p>
 *
 * @author Ximi
 * @since 2020-08-21
 */
public interface CoffeeMapper extends BaseMapper<Coffee> {

    public List<Coffee> findByName(@Param("name") String name);

}
