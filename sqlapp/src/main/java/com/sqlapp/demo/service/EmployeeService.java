package com.sqlapp.demo.service;

import java.util.List;

import com.sqlapp.demo.model.Department;
import com.sqlapp.demo.model.Employee;

public interface EmployeeService {
	List<Employee> getAllEmployees();
	void saveEmployee(Employee employee);
	void saveNewDepartment(Department department);
	
	Employee getEmployeeById(long id);
	void deleteEmployeeById(long id);
}
