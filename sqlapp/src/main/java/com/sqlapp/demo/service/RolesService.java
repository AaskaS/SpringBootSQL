package com.sqlapp.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sqlapp.demo.model.Roles;
import com.sqlapp.demo.repository.RolesRepository;

@Service
public class RolesService {

	@Autowired
	RolesRepository rolesRepository;
	
	
	public void saveRole(Roles role) {

		this.rolesRepository.save(role);
	}
	
	public Roles getRole(long id) {
		return this.rolesRepository.getOne(id);
	}
}
