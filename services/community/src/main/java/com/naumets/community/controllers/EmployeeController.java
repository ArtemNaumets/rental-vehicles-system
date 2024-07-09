package com.naumets.community.controllers;

import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

import com.naumets.community.models.hrConfigurations.EmployeeType;
import com.naumets.community.models.hrConfigurations.JobTitle;
import com.naumets.community.models.locations.Country;
import com.naumets.community.models.locations.State;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.naumets.community.models.Employee;
import com.naumets.community.services.EmployeeService;

@RestController
@RequestMapping("/api/community/employees")
public class EmployeeController {

	@Autowired private EmployeeService employeeService;

	@GetMapping("/")
	public List<Employee> findAll() {
		return employeeService.findAll();
	}

	@GetMapping("/findById/{id}")
	public ResponseEntity<Optional<Employee>> findById(@PathVariable Integer id) {
		Optional<Employee> employee = employeeService.findById(id);
		return employee.isPresent() ? ResponseEntity.ok(employee) : ResponseEntity.notFound().build();
	}

	@PostMapping("/add")
	public ResponseEntity<Employee> addNew(@RequestBody Employee employee) {
		Employee savedEmployee = employeeService.save(employee);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedEmployee);
	}

	@PutMapping("/update/")
	public ResponseEntity<Employee> update(@RequestBody Employee employee) {
		Employee updatedEmployee = employeeService.save(employee);
		return ResponseEntity.ok(updatedEmployee);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		employeeService.delete(id);
		return ResponseEntity.noContent().build();
	}


	@PostMapping("/uploadPhoto")
	public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file, Principal principal) throws IOException {
		// User user = userService.findByUsername(principal.getName());
		// userService.savePhoto(file, user);
		return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body("Photo upload functionality not implemented yet.");
	}
}
