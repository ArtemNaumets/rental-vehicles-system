package com.naumets.community.services;

import com.naumets.community.models.Employee;
import com.naumets.community.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    public Optional<Employee> findById(String id) {
        return employeeRepository.findById(id);
    }

    public void deleteById(String id) {
        employeeRepository.deleteById(id);
    }

    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee findByUsername(String username) {
        return employeeRepository.findByUsername(username);
    }

    public long countAll() {
        return employeeRepository.count();
    }

    public long countByFirstnameAndLastname(String firstname, String lastname) {
        return employeeRepository.countByFirstnameAndLastname(firstname, lastname);
    }

    public List<Employee> findByFirstnameAndLastname(String firstname, String lastname) {
        return employeeRepository.findByFirstnameAndLastname(firstname, lastname);
    }
}
