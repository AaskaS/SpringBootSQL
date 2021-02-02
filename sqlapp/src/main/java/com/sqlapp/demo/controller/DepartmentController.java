package com.sqlapp.demo.controller;

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

import com.sqlapp.demo.model.Department;
import com.sqlapp.demo.service.DepartmentService;

@Controller
@RequestMapping("/department")
public class DepartmentController {

	@Autowired
	private DepartmentService departmentService;

	@GetMapping("/show-new-department-form")
	public String showNewDepartmentForm(Model model) {
		// create model attribute to bind form data
		Department department = new Department();
		model.addAttribute("department", department);
		model.addAttribute("listDepartment", departmentService.getAllDepartment());
		return "add_department";
	}

	@PostMapping("/saveDepartment")
	public String saveDepartment(@Valid @ModelAttribute("department") Department department, BindingResult result,
			Model model) {
		if (result.hasErrors()) {
			model.addAttribute("listDepartment", departmentService.getAllDepartment());
			return "add_department";
		}
		else if(departmentService.checkDep(department.getDepartName()) || departmentService.checkDepID(department.getDeptId())) {
			model.addAttribute("listDepartment", departmentService.getAllDepartment());
			String message = departmentService.checkDep(department.getDepartName()) ? "Department Name Exists": "Department ID Exists";
			model.addAttribute("duplicate", message);
		
			return "add_department";
		}
		else {
			
			departmentService.saveNewDepartment(department);
			return "redirect:/main";
		}
	}

	@GetMapping("/deleteDepartment/{id}")
	public String deleteDepartment(@PathVariable(value = "id") long id) {
		// call delete employee method
		this.departmentService.deleteDepartmentById(id);
		return "redirect:/main";
	}
}
