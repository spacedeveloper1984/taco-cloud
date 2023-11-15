package com.spring.in.action.tacocloud.dao;

import com.spring.in.action.tacocloud.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
