package com.digimenu.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.digimenu.main.security.PasswordResetToken;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken,Integer>{
	PasswordResetToken findByToken(String token);
}
