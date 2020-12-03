package com.sqlapp.demo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sqlapp.demo.model.Department;
import com.sqlapp.demo.model.Employee;
import com.sqlapp.demo.model.EmployeeInfo;
import com.sqlapp.demo.service.EmployeeService;


@Controller
public class EmployeeController {
	@Autowired
	private EmployeeService employeeService;

	// display list of employees
	@GetMapping("/")
	public String viewHomePage(Model model) {
		model.addAttribute("listEmployees", employeeService.getAllEmployees());
		return "index";
	}

	@GetMapping("/showNewEmployeeForm")
	public String showNewEmployeeForm(Model model) {
		// create model attribute to bind form data
		Employee employee = new Employee();
		Department department = new Department();
		model.addAttribute("employee", employee);
		model.addAttribute("department", department);
		return "new_employee";

	}
	@PostMapping("/saveEmployee")
	//public String saveEmployee(@Valid @ModelAttribute("department") Department department, BindingResult result) {
	public String saveEmployee(@Valid @ModelAttribute("employee") Employee employee, BindingResult result) {
		//public String saveEmployee(@ModelAttribute("employee") Employee employee) {
		//save employee to db
		System.out.println("Aaska, I am trying to save");
		
		if (result.hasErrors() ) {
			return "new_employee";
		}
		else {	
			
			//employeeService.saveEmployee(department.getEmployees());
			employeeService.saveEmployee(employee);
			//employeeService.saveDepartmentTwo(department);
			
			
			return "redirect:/";
		}
		
	}
	
	@GetMapping("/showNewDepartmentForm")
	public String showNewDepartmentForm(Model model) {
		// create model attribute to bind form data
		Department department = new Department();
		model.addAttribute("department", department);
		return "add_department";

	}
	@PostMapping("/saveDepartment")
	public String saveDepartment(@Valid @ModelAttribute("department") Department department, BindingResult result) {
		//public String saveEmployee(@ModelAttribute("employee") Employee employee) {
		//save employee to db
		System.out.println("Aaska, I am trying to save DEPARTMENT");
		
		if (result.hasErrors() ) {
			return "add_department";
		}
		else {	
			
			//employeeService.saveEmployee(department.getEmployees());
			employeeService.saveNewDepartment(department);
			//employeeService.saveDepartmentTwo(department);
			
			
			return "redirect:/";
		}
		
	}
	
	
	@PostMapping("/saveProfile")
	public String saveProfile(@Valid @ModelAttribute("employeeinfo") EmployeeInfo employeeInfo, BindingResult result) {
		//public String saveEmployee(@ModelAttribute("employee") Employee employee) {
		//save employee to db
		
		System.out.println("Aaska, I am trying to save PROFILE");
		Employee employee = employeeService.getEmployeeById(employeeInfo.getTempid());
		employeeInfo.setEmployee(employee);
		System.out.println(employeeInfo.getEmployee().getFirstName());
		//System.out.println(employeeInfo.getEmployee().getId());
		
		if (result.hasErrors() ) {
			return "add_profile";
		}
		else {	
			
			//employeeService.saveEmployee(department.getEmployees());
			employeeService.saveNewProfile(employeeInfo);
			//employeeService.saveDepartmentTwo(department);
			
			
			return "redirect:/";
		}
		
	}
/*
	@PostMapping("/saveEmployee")
	public String saveEmployee(@Valid @ModelAttribute("employee") Employee employee, BindingResult result) {
		//public String saveEmployee(@ModelAttribute("employee") Employee employee) {
		//save employee to db
		System.out.println(employee.getEmail());
		if (result.hasErrors() ) {
			return "new_employee";
		}
		else {	
			employeeService.saveEmployee(employee);
			return "redirect:/";
		}
		
	}
*/
	@GetMapping("/showProfileUpdate/{id}")
	public String showProfileUpdate(@PathVariable(value = "id") long id, Model model) {
		// get employee from the service
		try {
			EmployeeInfo employeeInfo = employeeService.getProfileById(id);
			System.out.println(employeeInfo.getEmployee().getId());
		// set employee as a model attribute to pre-populate the form
			employeeInfo.setTempid(id);
			model.addAttribute("employeeInfo", employeeInfo);
			
			System.out.println("Aaska, I am at new profile");
			

			return "add_profile";
		}catch(Exception e) {
			System.out.println("i am here instead");
			
			EmployeeInfo employeeInfo = new EmployeeInfo();
			employeeInfo.setTempid(id);
			model.addAttribute("employeeInfo", employeeInfo);
			
			//Employee employee = employeeService.getEmployeeById(id);
			//employeeInfo.setEmployee(employee);
			//employeeService.saveNewProfile(employeeInfo);
			
			
			
			return "add_profile";
		}
		
		// tutorial said to use "update_employee" but ultimately it's the same as
		// using "new_employee" + hidden id field
	}

	@GetMapping("/showFormForUpdate/{id}")
	public String showFormForUpdate(@PathVariable(value = "id") long id, Model model) {
		// get employee from the service
		Employee employee = employeeService.getEmployeeById(id);

		// set employee as a model attribute to pre-populate the form
		model.addAttribute("employee", employee);

		return "new_employee";
		// tutorial said to use "update_employee" but ultimately it's the same as
		// using "new_employee" + hidden id field
	}

	@GetMapping("/deleteEmployee/{id}")
	public String deleteEmployee(@PathVariable(value = "id") long id) {
		// call delete employee method
		this.employeeService.deleteEmployeeById(id);

		return "redirect:/";
	}

}
