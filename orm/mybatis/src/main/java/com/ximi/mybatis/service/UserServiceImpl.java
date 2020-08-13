package com.ximi.mybatis.service;

import com.ximi.mybatis.entity.User;
import com.ximi.mybatis.mapper.UserMapper;
import com.ximi.mybatis.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Ximi
 * @since 2020-08-13
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public User getOneByName(String name) {
        return userMapper.getOneByName(name);
    }
}
