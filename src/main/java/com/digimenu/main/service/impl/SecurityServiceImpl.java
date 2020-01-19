package com.digimenu.main.service.impl;

import com.digimenu.main.service.SecurityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
@Service
public class SecurityServiceImpl implements SecurityService {


	private UserDetailsService userDetailsService;
	@Autowired
	public SecurityServiceImpl(@Qualifier("customUserDetailsService") UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}


	    @Override
	    public String findLoggedInUsername() {
	        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	        if (principal!=null) {
	            UserDetails ud=(UserDetails)principal;
	            return ud.getUsername();
	        }
	        return null;
	    }
	    


}
