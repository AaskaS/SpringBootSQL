package com.sqlapp.demo.service;

import java.util.List;

import com.sqlapp.demo.model.EmployeeInfo;

public interface EmployeeInfoService {
	List<EmployeeInfo> getAllProfileInfo();
	void saveNewProfile(EmployeeInfo employeeInfo);
	EmployeeInfo getProfileById(long id);

}
