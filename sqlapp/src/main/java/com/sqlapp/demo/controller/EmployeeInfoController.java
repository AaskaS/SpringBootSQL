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

import com.sqlapp.demo.model.Employee;
import com.sqlapp.demo.model.EmployeeInfo;
import com.sqlapp.demo.service.EmployeeInfoService;
import com.sqlapp.demo.service.EmployeeService;

@Controller
@RequestMapping("/info")
public class EmployeeInfoController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private EmployeeInfoService employeeInfoService;

	@GetMapping("/showProfileUpdate/{id}")
	public String showProfileUpdate(@PathVariable(value = "id") long id, Model model) {
		// get employee from the service
		try {
			Employee employee = employeeService.getEmployeeById(id);			
			EmployeeInfo employeeInfo = employee.getEmployeeinfo();
			
			// set employee as a model attribute to pre-populate the form
			employeeInfo.setTempid(id);
			model.addAttribute("employeeInfo", employeeInfo);
			model.addAttribute("listCountries", employeeInfoService.getAllCountries());


			return "add_profile";
		} catch (Exception e) {

			EmployeeInfo employeeInfo = new EmployeeInfo();
			employeeInfo.setTempid(id);
			model.addAttribute("employeeInfo", employeeInfo);
			model.addAttribute("listCountries", employeeInfoService.getAllCountries());


			return "add_profile";
		}
	}

	@PostMapping("/saveProfile")
	public String saveProfile(@Valid @ModelAttribute("employeeinfo") EmployeeInfo employeeInfo, BindingResult result) {
		Employee employee = employeeService.getEmployeeById(employeeInfo.getTempid());
		employeeInfo.setEmployee(employee);
		System.out.println("INSIDE SAVE " + employeeInfo.getCountry());
		
		if (result.hasErrors()) {
			return "add_profile";
		} else {

			employeeInfoService.saveNewProfile(employeeInfo);

			return "redirect:/main";
		}
	}
}
