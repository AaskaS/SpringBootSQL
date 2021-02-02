package com.sqlapp.demo.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "employees")
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;

	@Valid
	@NotBlank(message = "Field cannot be blank")
	@Size(min = 2, max = 30, message = "First name must be between 2-30 characters")
	@Column(name = "first_name")
	private String firstName;

	@Valid
	@NotBlank(message = "Field cannot be blank")
	@Size(min = 2, max = 30, message = "Last name must be between 2-30 characters")
	@Column(name = "last_name")
	private String lastName;

	@Valid
	@NotBlank(message = "Field cannot be blank")
	@Pattern(regexp="^[A-Za-z0-9]+[-._]{0,1}+[A-Za-z0-9]+@[a-zA-Z0-9]++\\.[a-zA-Z]{2,}$", message = "Please provide a valid email address")
	@Column(name = "email")
	private String email;
//regexp="[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$"
//regexp = ".+@.+\\..+"
	@Column(name = "department_id")
	@NotNull(message = "Field cannot be blank")
	private long department_id;
	
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "employee")
	private EmployeeInfo employeeinfo;

	

	/*
	 * @ManyToOne(fetch=FetchType.EAGER,cascade = CascadeType.ALL)
	 * 
	 * @JoinColumn( foreignKey = @ForeignKey(name = "dept_id"), name =
	 * "department_id", nullable = false , insertable = false, updatable=false )
	 * //@OnDelete(action = OnDeleteAction.CASCADE) private Department department;
	 */

	/*
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "dept", referencedColumnName = "deptId")
	private Department department;
	*/

	/*
	 * @Valid
	 * 
	 * @NotNull(message = "Field cannot be blank")
	 * 
	 * @Min(value = 1, message = "Invalid. Department IDs are between 1-9")
	 * 
	 * @Max(value = 9, message = "Invalid. Department IDs are between 1-9")
	 * 
	 * @Column(name = "dep_id") private long departId;
	 */

	/*
	 * @ManyToOne
	 * 
	 * @JoinColumn(name = "dep_id", nullable = true) private Department department;
	 */

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	

	public long getDepartment_id() {
		return department_id;
	}

	public void setDepartment_id(long department_id) {
		this.department_id = department_id;
	}

	public EmployeeInfo getEmployeeinfo() {
		return employeeinfo;
	}

	public void setEmployeeinfo(EmployeeInfo employeeinfo) {
		this.employeeinfo = employeeinfo;
	}

	
	
	/*
	 * public Department getDepartment() { return department; }
	 * 
	 * public void setDepartment(Department department) { this.department =
	 * department; }
	 */
}
