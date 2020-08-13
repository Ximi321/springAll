package com.ximi.mybatis.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ximi.mybatis.entity.User;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author Ximi
 * @since 2020-08-13
 */
public interface IUserService extends IService<User> {

    /**
     * 根据名称获取一条数据
     *
     * @param name
     * @return
     */
    public User getOneByName(String name);

}
