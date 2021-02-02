package com.sqlapp.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sqlapp.demo.model.EmployeeInfo;
import com.sqlapp.demo.repository.EmployeeInfoRepository;

@Service
public class EmployeeInfoService {

	@Autowired
	private EmployeeInfoRepository employeeInfoRepository;

	public List<EmployeeInfo> getAllProfileInfo() {
		return employeeInfoRepository.findAll();
	}

	public void saveNewProfile(EmployeeInfo employeeInfo) {
		this.employeeInfoRepository.save(employeeInfo);
	}

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

	public void deleteEmployeeInfoById(long id) {
		employeeInfoRepository.deleteById(id);

	}

	public List<String> getAllCountries() {
		List<String> countries = new ArrayList<String>(); // = new String[Locale.getISOCountries().length];
		String[] countryCodes = Locale.getISOCountries();
		for (int i = 0; i < countryCodes.length; i++) {
			Locale obj = new Locale("", countryCodes[i]);
			countries.add(obj.getDisplayCountry());
		}
		java.util.Collections.sort(countries);
		return countries;
	}

}
