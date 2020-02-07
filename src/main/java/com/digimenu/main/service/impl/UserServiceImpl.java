package com.digimenu.main.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import com.digimenu.main.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.digimenu.main.repository.RoleRepository;
import com.digimenu.main.repository.UserRepository;
import com.digimenu.main.security.Role;
import com.digimenu.main.security.User;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public void save(User user) throws Exception {

        if (!user.getPassword().contains("$2a$10$")) {
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        }

        user.setRoles(new ArrayList<Role>(Arrays.asList(roleRepository.getOne(3)))); // direk user rolunde ekliyor
        userRepository.save(user);
    }

    @Override
    public void forgetPassUserSave(User user) throws Exception {

        if (!user.getPassword().contains("$2a$10$")) {
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        }

        if (user.getRoles().contains(roleRepository.getOne(2))) {
            user.setRoles(new ArrayList<Role>(Arrays.asList(roleRepository.getOne(3), roleRepository.getOne(2))));
            userRepository.save(user);
        } else {
            user.setRoles(new ArrayList<Role>(Arrays.asList(roleRepository.getOne(3))));
            userRepository.save(user);
        }
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
