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
//	    @Autowired
//	    private AuthenticationManager authenticationManager;

	private UserDetailsService userDetailsService;
	@Autowired
	public SecurityServiceImpl(@Qualifier("customUserDetailsService") UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}


	    @Override
	    public String findLoggedInUsername() {
	        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	        //System.out.println(principal);
	        
	        if (principal!=null) {
	            UserDetails ud=(UserDetails)principal;
	            return ud.getUsername();
	        }

	        return null;
	    }
	    
//	    @Override
//	    public void autoLogin(String username, String password) {
//	        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
//	        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
//
//	        authenticationManager.authenticate(usernamePasswordAuthenticationToken);
//
//	        if (usernamePasswordAuthenticationToken.isAuthenticated()) {
//	            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
//	            logger.debug(String.format("Auto login %s successfully!", username));
//	        }
//	    }

}
