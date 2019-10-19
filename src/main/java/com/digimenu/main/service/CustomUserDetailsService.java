package com.digimenu.main.service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.transaction.Transactional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.digimenu.main.repository.UserRepository;
import com.digimenu.main.security.Role;
import com.digimenu.main.security.User;
@Service
public class CustomUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;
	protected final Log logger = LogFactory.getLog(getClass());
	@Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
//		boolean isEnabled=true;
		boolean accountNonExpired = true;
	    boolean credentialsNonExpired = true;
	    boolean accountNonLocked = true;
        User user = userRepository.findByUsername(username);
        
        if (user == null) {
        	throw  new UsernameNotFoundException(username +" not found");
        }
        
        org.springframework.security.core.userdetails.User authUser = new org.springframework.security.core.userdetails.User(
        		user.getUsername(),
        		user.getPassword(),
        		user.isEnabled(),
        		accountNonExpired, 
                credentialsNonExpired, 
                accountNonLocked, 
        		getAuthorities(user));
        
     return authUser;
	}
        
	
	private static Collection<? extends GrantedAuthority> getAuthorities(User user)
    {
        String[] userRoles = user.getRoles()
                                    .stream()
                                    .map((role) -> role.getName())
                                    .toArray(String[]::new);
        Collection<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(userRoles);
        return authorities;
    }
}
