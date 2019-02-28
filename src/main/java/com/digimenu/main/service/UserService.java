package com.digimenu.main.service;

import com.digimenu.main.security.User;

public interface UserService {
	void save(User user);
	User findByUsername(String username);
	User findByEmail(String email);
}
