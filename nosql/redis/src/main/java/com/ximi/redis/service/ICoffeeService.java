package com.ximi.redis.service;

import com.ximi.redis.entity.Coffee;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 咖啡 服务类
 * </p>
 *
 * @author Ximi
 * @since 2020-08-21
 */
public interface ICoffeeService extends IService<Coffee> {

    public List<Coffee> findByName(String name);

}
