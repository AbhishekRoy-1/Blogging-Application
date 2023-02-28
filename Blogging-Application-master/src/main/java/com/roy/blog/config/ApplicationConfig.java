package com.roy.blog.config;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.roy.blog.entities.User;
import com.roy.blog.exceptions.ResourceNotFoundException;
import com.roy.blog.repositories.UserRepo;

@Service
@AllArgsConstructor
public class ApplicationConfig implements UserDetailsService {
	@Autowired
	private final UserRepo userRepo;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user=this.userRepo.findByEmail(username)
				.orElseThrow(()-> {
					return new ResourceNotFoundException("User", "Email Id : " + username, 0);
				});

		return user;
	}
	@Bean
	public UserDetailsService userDetailsService() {
		return username->  userRepo.findByEmail(username)
				.orElseThrow(()->new UsernameNotFoundException("User not Found !"));
	}
	@Bean
	public AuthenticationProvider authenticationProvider(){
		DaoAuthenticationProvider authProvider=new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService());
		authProvider.setPasswordEncoder(passwordEncoder());
		return authProvider;
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
