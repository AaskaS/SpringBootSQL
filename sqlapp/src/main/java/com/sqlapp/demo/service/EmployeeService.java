package com.sqlapp.demo.service;

import java.util.List;
import java.util.Map;

import com.sqlapp.demo.model.Department;
import com.sqlapp.demo.model.Employee;

public interface EmployeeService {
	List<Employee> getAllEmployees();
	void saveEmployee(Employee employee);
	void saveNewDepartment(Department department);
	
	Employee getEmployeeById(long id);
	void deleteEmployeeById(long id);
	void deleteDepartmentById(long id);
	List<Department> getAllDepartment();
	Map<Long,String> getDepartmentMap();
}
