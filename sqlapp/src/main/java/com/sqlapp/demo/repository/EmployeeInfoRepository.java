package com.sqlapp.demo.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sqlapp.demo.model.EmployeeInfo;


@Repository
public interface EmployeeInfoRepository extends JpaRepository<EmployeeInfo, Long> {
	

}
