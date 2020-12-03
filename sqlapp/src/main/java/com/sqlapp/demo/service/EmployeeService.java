package com.sqlapp.demo.service;

import java.util.List;

import com.sqlapp.demo.model.Department;
import com.sqlapp.demo.model.Employee;
import com.sqlapp.demo.model.EmployeeInfo;

public interface EmployeeService {
	List<Employee> getAllEmployees();
	List<EmployeeInfo> getAllProfileInfo();
	//void saveDepartmentTwo(Department department);
	void saveEmployee(Employee employee);
	void saveNewDepartment(Department department);
	void saveNewProfile(EmployeeInfo employeeInfo);
	Employee getEmployeeById(long id);
	EmployeeInfo getProfileById(long id);
	void deleteEmployeeById(long id);
}
