package com.sqlapp.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "employeeinfo")
public class EmployeeInfo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "number")
	@Size(max = 15)
	private String number;
	
	@Column(name="country")
	@Size(max=100)
	private String country;
	
	private long tempid;
	
	@OneToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "employees_id", insertable = true, updatable = true, nullable=true)
	private Employee employee;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public long getTempid() {
		return tempid;
	}

	public void setTempid(long tempid) {
		this.tempid = tempid;
	}
	
	
	
	
	
	

}
