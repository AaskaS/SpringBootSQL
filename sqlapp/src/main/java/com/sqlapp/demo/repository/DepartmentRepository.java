package com.sqlapp.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sqlapp.demo.model.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
	List<Department> findByDeptId(long dept_Id);
	


}
