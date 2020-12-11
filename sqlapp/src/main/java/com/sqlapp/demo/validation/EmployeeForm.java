package com.sqlapp.demo.validation;


import javax.validation.constraints.*;



public class EmployeeForm {

		@NotNull
		@Size(min=2, max=30)
		private String firstName;
		
		@NotNull
		@Size(min=2, max=30)
		private String lastName;
		
		@Email
		private String email;
		
		@NotNull
		private long department_id;
		
}










