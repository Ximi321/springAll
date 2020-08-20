package com.ximi.cache.service;

import com.ximi.cache.entity.User;
import com.ximi.cache.mapper.UserMapper;
import com.ximi.cache.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Ximi
 * @since 2020-08-20
 */
@Service
@Slf4j
@CacheConfig(cacheNames = "user")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Resource
    private UserMapper userMapper;

    @Cacheable
    @Override
    public List<User> findByName(String name) {
        log.info("find user by name");
        return userMapper.findByName(name);
    }
}
