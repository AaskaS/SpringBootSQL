package com.sqlapp.demo.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NaturalId;

import com.sun.istack.NotNull;

@Entity
@Table(name = "department")
public class Department {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	//@GeneratedValue
	@Column(name = "id")
	private long id;
	
	@NaturalId
	@JoinColumn(name = "dept_id", referencedColumnName = "dept")
    private long deptId;
	

	/*
	 * @Column(name = "dep_id") public long departId;
	 */

	@Column(name = "dept_name", unique=true)
	public String departName;

	//@OneToMany( mappedBy = "department", cascade = CascadeType.ALL, orphanRemoval = true)
	//@OneToMany(cascade=CascadeType.ALL, orphanRemoval = true)
	//private List<Employee> employees;

		
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	/*
	 * public long getDepartId() { return departId; }
	 * 
	 * public void setDepartId(long departId) { this.departId = departId; }
	 */

	public String getDepartName() {
		return departName;
	}

	public void setDepartName(String departName) {
		this.departName = departName;
	}

	public long getDeptId() {
		return deptId;
	}

	public void setDeptId(long deptId) {
		this.deptId = deptId;
	}

	/*
	 * public List<Employee> getEmployees() { return employees; }
	 * 
	 * public void setEmployees(List<Employee> employees) { this.employees =
	 * employees; }
	 */

}
