package com.digimenu.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.digimenu.main.security.ConfirmationToken;

public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken, String> {  
    ConfirmationToken findByConfirmationToken(String confirmationToken);
}
