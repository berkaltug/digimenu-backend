package com.digimenu.main.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.digimenu.main.security.User;

public interface UserRepository extends JpaRepository<User, Integer>
{
    User findByUsername(String username);
    User findByEmail(String email);
}