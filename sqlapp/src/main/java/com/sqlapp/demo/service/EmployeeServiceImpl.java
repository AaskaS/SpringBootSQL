package com.sqlapp.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sqlapp.demo.repository.DepartmentRepository;

import com.sqlapp.demo.repository.EmployeeRepository;
import com.sqlapp.demo.model.Department;
import com.sqlapp.demo.model.Employee;


@Service
public class EmployeeServiceImpl implements EmployeeService {
	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private DepartmentRepository departmentRepository;
	


	@Override
	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}
	

	
	@Override
	public void saveNewDepartment(Department department) {
		
		this.departmentRepository.save(department);
	}

	@Override
	public void saveEmployee(Employee employee) {
		this.employeeRepository.save(employee);

		

	}

	@Override
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
	


	@Override
	public void deleteEmployeeById(long id) {
		this.employeeRepository.deleteById(id);

	}

}
