package com.dto.testdto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dto.testdto.model.User;

public interface UserRepository extends JpaRepository<User, String> {
    
}
