package com.naumets.community.services;

import java.util.List;
import java.util.Optional;

import com.naumets.community.models.Employee;
import com.naumets.community.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class EmployeeService {
		
	@Autowired
	private EmployeeRepository employeeRepository;

//	@Autowired
//	UserRepository userRepository;
	
	public List<Employee> findAll(){
		return employeeRepository.findAll();
	}	
	
	public Optional<Employee> findById(int id) {
		return employeeRepository.findById(id);
	}	
	
	public void delete(int id) {
		employeeRepository.deleteById(id);
	}
	
	public Employee save(Employee employee) {
		employeeRepository.save(employee);
        return employee;
    }
	
	public Employee findByUsername(String un) {
		return employeeRepository.findByUsername(un);
	}

//	public User findUserByEmployee(Employee employee){
//		try {
//			User user = userRepository.findByFirstnameAndLastname(
//					employee.getFirstname(),
//					employee.getLastname());
//			return user;
//		}
//		catch (Exception ex){
//			return null;
//		}
//
//	}

	public Integer countAll() {
		return employeeRepository.countAllBy();
	}

	public Integer countByFirstnameAndLastname(String firstname, String lastname){
		return employeeRepository.countByFirstnameAndLastname(firstname, lastname);
	}

	public List<Employee> findByFirstnameAndLastname(String firstname, String lastname) {
		return employeeRepository.findByFirstnameAndLastname(firstname,lastname);
	}
}
