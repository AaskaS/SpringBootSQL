package com.sqlapp.demo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sqlapp.demo.model.Department;
import com.sqlapp.demo.model.Employee;
import com.sqlapp.demo.model.EmployeeInfo;
import com.sqlapp.demo.service.DepartmentService;
import com.sqlapp.demo.service.EmployeeInfoService;
import com.sqlapp.demo.service.EmployeeService;

@Controller
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private EmployeeInfoService employeeInfoService;

	@Autowired
	private DepartmentService departmentService;

	// display list of employees
	@GetMapping("/")
	public String home1() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
			return "login";
		}
		// return "login";
		return "redirect:/main";
	}

	@GetMapping("/main")
	public String main(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
			return "expired";
		}
		model.addAttribute("listEmployees", employeeService.getAllEmployees());
		return "index";
	}

	@GetMapping("/employee/showFormForUpdate/{id}")
	public String showFormForUpdate(@PathVariable(value = "id") long id, Model model) {
		// get employee from the service
		Employee employee = employeeService.getEmployeeById(id);

		// set employee as a model attribute to pre-populate the form

		List<Department> listID = departmentService.getAllDepartment();
		Map<Long, String> map = new HashMap<Long, String>();
		for (Department i : listID)
			map.put(i.getDeptId(), i.getDepartName());

		model.addAttribute("listID", map);
		model.addAttribute("employee", employee);

		return "new_employee";
		// tutorial said to use "update_employee" but ultimately it's the same as
		// using "new_employee" + hidden id field
	}

	@GetMapping("/employee/showNewEmployeeForm")
	public String showNewEmployeeForm(Model model) {
		// create model attribute to bind form data
		Employee employee = new Employee();
		Department department = new Department();

		Map<Long, String> map = departmentService.getDepartmentMap();

		model.addAttribute("listID", map);
		model.addAttribute("employee", employee);
		model.addAttribute("department", department);
		return "new_employee";

	}

	@PostMapping("/employee/saveEmployee")
	public String saveEmployee(@Valid @ModelAttribute("employee") Employee employee, BindingResult result,
			Model model) {
		// save employee to db

		if (result.hasErrors()) {
			Map<Long, String> map = departmentService.getDepartmentMap();

			model.addAttribute("listID", map);

			return "new_employee";
		} else if (employeeService.checkEmployee(employee.getEmail())) {
			Map<Long, String> map = departmentService.getDepartmentMap();

			model.addAttribute("listID", map);
			model.addAttribute("duplicate", "Email already exists!");

			return "new_employee";
		} else {

			employeeService.saveEmployee(employee);

			return "redirect:/main";
		}

	}

	@GetMapping("/employee/deleteEmployee/{id}")
	public String deleteEmployee(@PathVariable(value = "id") long id) {
		// call delete employee method
		Employee employee = employeeService.getEmployeeById(id);			
		EmployeeInfo employeeInfo = employee.getEmployeeinfo();
		
		try {
			this.employeeInfoService.deleteEmployeeInfoById(employeeInfo.getId());
			this.employeeService.deleteEmployeeById(id);
		}
		catch(Exception e) {
			this.employeeService.deleteEmployeeById(id);
		}
		

		return "redirect:/main";
	}




	@GetMapping("/about")
	public String about() {
		return "about";
	}

	/*
	 * @GetMapping("/login") public String login() { //
	 * this.employeeService.checkUser(); return "login"; }
	 */

	@GetMapping("/403")
	public String error403() {
		return "403";
	}
}
