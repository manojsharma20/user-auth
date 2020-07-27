package com.userauth.org.config;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.userauth.org.model.User;
import com.userauth.org.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	 private final UserRepository userRepository;

	    public CustomUserDetailsService(UserRepository userRepository) {
	        this.userRepository = userRepository;
	    }
	    
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		 User user = userRepository.findByEmailId(username);
	        if (user == null) {
	            throw new UsernameNotFoundException(username);
	        }
	        return new CustomUserPrincipal(user);
	}

}
