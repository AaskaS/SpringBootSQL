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

import com.sqlapp.demo.model.Department;
import com.sqlapp.demo.model.Employee;
import com.sqlapp.demo.model.EmployeeInfo;
import com.sqlapp.demo.service.EmployeeInfoService;
import com.sqlapp.demo.service.EmployeeService;

@Controller
public class EmployeeController {
	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private EmployeeInfoService employeeInfoService;

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
	public String saveEmployee(@Valid @ModelAttribute("employee") Employee employee, BindingResult result) {
		// save employee to db

		if (result.hasErrors()) {
			return "new_employee";
		} else {

			employeeService.saveEmployee(employee);

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
		// save employee to db

		if (result.hasErrors()) {
			return "add_department";
		} else {

			employeeService.saveNewDepartment(department);

			return "redirect:/";
		}

	}

	@GetMapping("/deleteEmployee/{id}")
	public String deleteEmployee(@PathVariable(value = "id") long id) {
		// call delete employee method
		this.employeeService.deleteEmployeeById(id);

		return "redirect:/";
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

	@GetMapping("/showProfileUpdate/{id}")
	public String showProfileUpdate(@PathVariable(value = "id") long id, Model model) {
		// get employee from the service
		try {
			Employee employee = employeeService.getEmployeeById(id);			
			EmployeeInfo employeeInfo = employee.getEmployeeinfo();
			
			// set employee as a model attribute to pre-populate the form
			employeeInfo.setTempid(id);
			model.addAttribute("employeeInfo", employeeInfo);


			return "add_profile";
		} catch (Exception e) {

			EmployeeInfo employeeInfo = new EmployeeInfo();
			employeeInfo.setTempid(id);
			model.addAttribute("employeeInfo", employeeInfo);

			return "add_profile";
		}

		// tutorial said to use "update_employee" but ultimately it's the same as
		// using "new_employee" + hidden id field
	}

	@PostMapping("/saveProfile")
	public String saveProfile(@Valid @ModelAttribute("employeeinfo") EmployeeInfo employeeInfo, BindingResult result) {
		// save employee to db

		Employee employee = employeeService.getEmployeeById(employeeInfo.getTempid());
		employeeInfo.setEmployee(employee);

		if (result.hasErrors()) {
			return "add_profile";
		} else {

			employeeInfoService.saveNewProfile(employeeInfo);

			return "redirect:/";
		}

	}


}
