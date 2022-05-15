package com.springboot.blog.repository;

import com.springboot.blog.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    //exist user in database or not
    Boolean existByUserName(String userName);
    Boolean existByEmail(String email);

}
