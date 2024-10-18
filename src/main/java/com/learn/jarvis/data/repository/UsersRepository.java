package com.learn.jarvis.data.repository;

import com.learn.jarvis.data.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends MongoRepository<User, Integer> {
  User findByUserName(String username);
}
