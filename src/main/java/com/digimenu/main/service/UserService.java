package com.digimenu.main.service;

import com.digimenu.main.security.User;

public interface UserService {
	void save(User user) throws Exception;
	void forgetPassUserSave(User user) throws Exception;
	User findByUsername(String username);
	User findByEmail(String email);
	User findLoggedInUser();
}
