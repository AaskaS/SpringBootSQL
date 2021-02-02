package com.sqlapp.demo.controller;

import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.sqlapp.demo.model.Roles;
import com.sqlapp.demo.model.User;
import com.sqlapp.demo.repository.RolesRepository;
import com.sqlapp.demo.service.EmployeeService;
import com.sqlapp.demo.service.RolesService;
import com.sqlapp.demo.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RolesService rolesService;
	
	@GetMapping("/signup")
	public String signup(Model model) {
		model.addAttribute("user", new User());

		return "signup";
	}

	@PostMapping("/register")
	public String success(@Valid @ModelAttribute("user") User user,BindingResult result, Model model) {
		
		Roles role = new Roles();
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
		if (result.hasErrors()) {
			if(this.userService.checkUser(user.getUsername())) {
				model.addAttribute("duplicate", "Username Exists");
			}
			return "signup";
		}

		else if (this.userService.checkUser(user.getUsername()) == true) { 
			model.addAttribute("duplicate", "Username Exists");
			return "signup";
		}

		else {
			role = this.rolesService.getRole(2);
			
			Set<Roles> r = new HashSet<>();
			r.add(role);
			user.setEnabled(true);
			
			user.setRoles(r);
			this.userService.saveUser(user);
			System.out.print("hi");
			
			return "success";
		}
	}


	@GetMapping("/login")
	public String login() {
		return "login";
	}

}
