package com.naumets.hrconfigurations.services;

import java.util.List;
import java.util.Optional;

import com.naumets.hrconfigurations.models.EmployeeType;
import com.naumets.hrconfigurations.repositories.EmployeeTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class EmployeeTypeService {
	
	@Autowired
	private EmployeeTypeRepository employeeTypeRepository;
	
	public List<EmployeeType> findAll(){
		return employeeTypeRepository.findAll();
	}	
	
	public Optional<EmployeeType> findById(int id) {
		return employeeTypeRepository.findById(id);
	}	
	
	public void delete(int id) {
		employeeTypeRepository.deleteById(id);
	}
	
	public EmployeeType save(EmployeeType employeeType) {
		employeeTypeRepository.save(employeeType);
		return employeeType;
	}

}
