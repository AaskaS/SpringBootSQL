package com.sqlapp.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sqlapp.demo.repository.DepartmentRepository;
import com.sqlapp.demo.repository.EmployeeInfoRepository;
import com.sqlapp.demo.repository.EmployeeRepository;
import com.sqlapp.demo.model.Department;
import com.sqlapp.demo.model.Employee;
import com.sqlapp.demo.model.EmployeeInfo;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private DepartmentRepository departmentRepository;
	
	@Autowired
	private EmployeeInfoRepository employeeInfoRepository;

	@Override
	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}
	
	@Override
	public List<EmployeeInfo> getAllProfileInfo() {
		return employeeInfoRepository.findAll();
	}
	
	@Override
	public void saveNewProfile(EmployeeInfo employeeInfo) {
		this.employeeInfoRepository.save(employeeInfo);
	}
	
	@Override
	public void saveNewDepartment(Department department) {
		
		this.departmentRepository.save(department);
	}

	@Override
	public void saveEmployee(Employee employee) {

		// System.out.println(department.getDepartId());

		/*Employee employee = new Employee();

		employee.setFirstName(department.getEmployees().get(0).getFirstName());
		employee.setLastName(department.getEmployees().get(0).getLastName());
		employee.setEmail(department.getEmployees().get(0).getEmail());
		employee.setDeptId(department.getEmployees().get(0).getDeptId());
		
		employee.setDepartment(department);

		//
		this.departmentRepository.save(department);*/
		//employee.getDepartment().getDepartName();
		this.employeeRepository.save(employee);

		

	}
	/*
	@Override
	public void saveDepartmentTwo(Department department) {

		// System.out.println(department.getDepartId());
		//System.out.println(department.getId());
		//System.out.println(department.getEmployees().get(0).getDeptId());

		this.departmentRepository.save(department);

	}*/

	/*
	 * @Override public void saveEmployee(Employee employee) {
	 * System.out.println(employee.getDepartment()); /*Department dept =
	 * departmentRepository.findById(employee.getDepartment().getDepartId()).orElse(
	 * null);
	 * 
	 * if (dept == null) { System.out.println("hello null dept"); dept = new
	 * Department(); } dept.setDepartName(employee.getDepartment().getDepartName());
	 * employee.setDepartment(dept);
	 * 
	 * 
	 * this.employeeRepository.save(employee);
	 * 
	 * }
	 */

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
	public EmployeeInfo getProfileById(long id) {
		Optional<EmployeeInfo> optional = employeeInfoRepository.findById(id);
		EmployeeInfo employeeInfo = null;
		if (optional.isPresent()) {
			employeeInfo = optional.get();

		} else {
			throw new RuntimeException("Employee Info not found for id: " + id);

		}

		return employeeInfo;
	}

	@Override
	public void deleteEmployeeById(long id) {
		this.employeeRepository.deleteById(id);

	}

}
