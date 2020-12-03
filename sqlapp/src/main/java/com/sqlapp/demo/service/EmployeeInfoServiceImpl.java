package com.sqlapp.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sqlapp.demo.model.EmployeeInfo;
import com.sqlapp.demo.repository.EmployeeInfoRepository;

@Service
public class EmployeeInfoServiceImpl implements EmployeeInfoService {
	
	@Autowired
	private EmployeeInfoRepository employeeInfoRepository;
	
	@Override
	public List<EmployeeInfo> getAllProfileInfo() {
		return employeeInfoRepository.findAll();
	}
	
	@Override
	public void saveNewProfile(EmployeeInfo employeeInfo) {
		this.employeeInfoRepository.save(employeeInfo);
	}
	
	@Override
	public EmployeeInfo getProfileById(long id) {
		Optional<EmployeeInfo> optional = employeeInfoRepository.findById(id);
		
		EmployeeInfo employeeInfo = null;
		if (optional.isPresent()) {
			employeeInfo = optional.get();

		} else {
			throw new RuntimeException("Employee Info not found for id: " + id);

		}

		return employeeInfo;
	}

}
