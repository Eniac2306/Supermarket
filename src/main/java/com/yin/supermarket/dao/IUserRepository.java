package com.yin.supermarket.dao;
import com.yin.supermarket.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface IUserRepository extends MongoRepository<User,Long> {

    Optional<User> findByUserName(String username);

}
