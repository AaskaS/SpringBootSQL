package com.sqlapp.demo.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
	public List<Department> getAllDepartment(){
		return departmentRepository.findAll();
	}
	
	public Map<Long,String> getDepartmentMap(){
		List<Department> listID = departmentRepository.findAll();
		//System.out.println(listID.get(1).getDepartName());
		Map<Long,String> map = new HashMap<Long,String>();
		for (Department i : listID) map.put(i.getDeptId(),i.getDepartName());
		return map;
	}

	
	@Override
	public void saveNewDepartment(Department department) {
		
		this.departmentRepository.save(department);
	}

	@Override
	public void saveEmployee(Employee employee) {
		
		List<Department> department = departmentRepository.findByDeptId(employee.getDepartment_id());
	
		
		if(department.isEmpty()) {
			System.out.println("emptyyyy :(");
		}
		else {
			System.out.println(department.get(0).getDepartName());
		}
		
		
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
	public void deleteDepartmentById(long id) {
		this.departmentRepository.deleteById(id);
	}

	@Override
	public void deleteEmployeeById(long id) {
		this.employeeRepository.deleteById(id);

	}

}
