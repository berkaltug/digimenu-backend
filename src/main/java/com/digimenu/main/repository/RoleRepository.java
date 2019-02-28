package com.digimenu.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.digimenu.main.security.Role;

public interface RoleRepository extends JpaRepository<Role,Integer> {

}
