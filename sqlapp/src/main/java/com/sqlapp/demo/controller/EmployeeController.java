package com.sqlapp.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sqlapp.demo.model.Department;
import com.sqlapp.demo.model.Employee;
import com.sqlapp.demo.model.EmployeeInfo;
import com.sqlapp.demo.repository.DepartmentRepository;
import com.sqlapp.demo.service.EmployeeInfoService;
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
	
	@GetMapping("/showFormForUpdate/{id}")
	public String showFormForUpdate(@PathVariable(value = "id") long id, Model model) {
		// get employee from the service
		Employee employee = employeeService.getEmployeeById(id);

		// set employee as a model attribute to pre-populate the form
		
		List<Department> listID = employeeService.getAllDepartment();
		System.out.println(listID.get(1).getDepartName());
		Map<Long,String> map = new HashMap<Long,String>();
		for (Department i : listID) map.put(i.getDeptId(),i.getDepartName());
		
		
		model.addAttribute("listID", map);
		model.addAttribute("employee", employee);
		

		return "new_employee";
		// tutorial said to use "update_employee" but ultimately it's the same as
		// using "new_employee" + hidden id field
	}


	@GetMapping("/showNewEmployeeForm")
	public String showNewEmployeeForm(Model model) {
		// create model attribute to bind form data
		Employee employee = new Employee();
		Department department = new Department();

		Map<Long,String> map = employeeService.getDepartmentMap();
		
		
		model.addAttribute("listID", map);
		model.addAttribute("employee", employee);
		model.addAttribute("department", department);
		return "new_employee";

	}

	@PostMapping("/saveEmployee")
	public String saveEmployee(@Valid @ModelAttribute("employee") Employee employee, BindingResult result, Model model) {
		// save employee to db
		System.out.println("I AM HEREEEE");
		System.out.println(employee.getDepartment_id());
		

		if (result.hasErrors()) {
			Map<Long,String> map = employeeService.getDepartmentMap();
			
			model.addAttribute("listID", map);
			
			return "new_employee";
		} else {
			System.out.println("no errors");

			employeeService.saveEmployee(employee);

			return "redirect:/";
		}

	}

	@GetMapping("/showNewDepartmentForm")
	public String showNewDepartmentForm(Model model) {
		// create model attribute to bind form data
		Department department = new Department();
		model.addAttribute("department", department);
		model.addAttribute("listDepartment", employeeService.getAllDepartment());
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
	
	@GetMapping("/deleteDepartment/{id}")
	public String deleteDepartment(@PathVariable(value = "id") long id) {
		// call delete employee method
		this.employeeService.deleteDepartmentById(id);

		return "redirect:/";
	}

	@GetMapping("/deleteEmployee/{id}")
	public String deleteEmployee(@PathVariable(value = "id") long id) {
		// call delete employee method
		this.employeeService.deleteEmployeeById(id);

		return "redirect:/";
	}
	
	



}
