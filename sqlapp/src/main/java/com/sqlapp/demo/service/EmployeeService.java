package com.sqlapp.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.stereotype.Service;

import com.sqlapp.demo.model.Department;
import com.sqlapp.demo.model.Employee;
import com.sqlapp.demo.model.User;
import com.sqlapp.demo.repository.DepartmentRepository;
import com.sqlapp.demo.repository.EmployeeRepository;
import com.sqlapp.demo.repository.UserRepository;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private DepartmentRepository departmentRepository;

	public void deleteEmployeeById(long id) {
		employeeRepository.deleteById(id);
	}

	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}

	public void saveEmployee(Employee employee) {
		List<Department> department = departmentRepository.findByDeptId(employee.getDepartment_id());

		if (department.isEmpty()) {
			System.out.println("emptyyyy :(");
		} else {
			System.out.println(department.get(0).getDepartName());
		}
		this.employeeRepository.save(employee);
	}

	public Employee getEmployeeById(long id) {
		Optional<Employee> optional = employeeRepository.findById(id);
		Employee employee = null;
		if (optional.isPresent()) {
			employee = optional.get();

		} else {
			throw new RuntimeException("Employee not found for id: " + id);
		}
		return employee;
	}

	public Boolean checkEmployee(String email) {
		try {
			String employee = employeeRepository.findByEmail(email).get(0).getEmail();
			if (employee.length() > 0) {
				return true;
			}
		} catch (Exception e) {
			return false;
		}

		return false;
	}

	/*
	 * public void saveUser(User user) { System.out.println("Sign Up");
	 * 
	 * this.userRepository.save(user); }
	 * 
	 * public Boolean checkUser(String name) { //AuthenticationManagerBuilder auth =
	 * null;
	 * //System.out.println(auth.getDefaultUserDetailsService().loadUserByUsername(
	 * "aaska").getPassword()); try { String user =
	 * userRepository.findByUsername(name).get(0).getUsername(); if(user.length() >
	 * 0){ return true; } }catch(Exception e) { return false; }
	 * 
	 * 
	 * return false;
	 * 
	 * }
	 */
}
