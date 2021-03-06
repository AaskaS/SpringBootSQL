package com.sqlapp.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sqlapp.demo.model.Employee;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	List<Employee> findByEmail(String email);
	

}
