package com.digimenu.main.service;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.digimenu.main.repository.RoleRepository;
import com.digimenu.main.repository.UserRepository;
import com.digimenu.main.security.Role;
import com.digimenu.main.security.User;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public void save(User user) {
		if(!user.getPassword().contains("$2a$10$")) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		}
		user.setRoles(new ArrayList<Role>(Arrays.asList(roleRepository.getOne(3)))); // direk user rolunde ekliyor
        userRepository.save(user);	
	}

	@Override
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}
}
