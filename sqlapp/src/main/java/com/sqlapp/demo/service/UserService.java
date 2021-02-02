package com.sqlapp.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.stereotype.Service;

import com.sqlapp.demo.model.User;
import com.sqlapp.demo.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public void saveUser(User user) {
		System.out.println("Sign Up");

		this.userRepository.save(user);
	}

	public Boolean checkUser(String name) {
		//AuthenticationManagerBuilder auth = null;
		//System.out.println(auth.getDefaultUserDetailsService().loadUserByUsername("aaska").getPassword());
		try {
			String user = userRepository.findByUsername(name).get(0).getUsername();
		if(user.length() > 0){
			return true;
		}
		}catch(Exception e) {
			return false;
		}
		
		
		return false;
		
	}
	

}
