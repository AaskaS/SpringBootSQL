package com.sqlapp.demo.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sqlapp.demo.model.Department;
import com.sqlapp.demo.repository.DepartmentRepository;

@Service
public class DepartmentService {

	@Autowired
	private DepartmentRepository departmentRepository;

	public void deleteDepartmentById(long id) {
		departmentRepository.deleteById(id);
	}

	public List<Department> getAllDepartment() {
		return departmentRepository.findAll();
	}

	public Map<Long, String> getDepartmentMap() {
		List<Department> listID = departmentRepository.findAll();
		// System.out.println(listID.get(1).getDepartName());
		Map<Long, String> map = new HashMap<Long, String>();
		for (Department i : listID)
			map.put(i.getDeptId(), i.getDepartName());
		return map;
	}

	public Boolean checkDepID(Long dept_Id) {
		try {
			long dep = departmentRepository.findByDeptId(dept_Id).get(0).getDeptId();
			if (dep > 0) {
				return true;
			}
		} catch (Exception e) {
			return false;
		}

		return false;
	}

	public Boolean checkDep(String dept_name) {
		try {
			String dep = departmentRepository.findByDepartName(dept_name).get(0).getDepartName();
			if (dep.length() > 0) {
				return true;
			}
		} catch (Exception e) {
			return false;
		}

		return false;
	}

	public void saveNewDepartment(Department department) {
		departmentRepository.save(department);
	}
}
