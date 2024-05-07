package com.scm.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.scm.entities.User;

public interface UserRepo extends JpaRepository<User, String>{
    //extra methods db related operations
    //custom query methods
    //custom finder methods

    Optional<User> findByEmail(String email);
    
    Optional<User> findByEmailAndPassword(String email, String password);
}
