package com.iege.cryptocurrency.repository;


import com.iege.cryptocurrency.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserRepository extends MongoRepository<User, String> {

    User findById(String Id);
    User findByUserName(String userName);
    User findByEmail(String email);
    List<User> findAll();

}

