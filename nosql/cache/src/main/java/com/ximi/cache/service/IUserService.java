package com.ximi.cache.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ximi.cache.entity.User;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author Ximi
 * @since 2020-08-20
 */
public interface IUserService extends IService<User> {

    /**
     * 通过用户名查找用户
     *
     * @param name
     * @return
     */
    List<User> findByName(String name);

}
