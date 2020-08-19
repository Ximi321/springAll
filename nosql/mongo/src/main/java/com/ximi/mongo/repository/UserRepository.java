package com.ximi.mongo.repository;

import com.ximi.mongo.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * user repository
 *
 * @author Ximi
 * @since 2020/08/19
 */
public interface UserRepository extends MongoRepository<User,String> {

    public List<User> findByName(String name);

}
