package com.naumets.hrconfigurations.controllers;

import java.util.List;
import java.util.Optional;

import com.naumets.hrconfigurations.models.EmployeeType;
import com.naumets.hrconfigurations.services.EmployeeTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/hrConfigurations/employeeTypes")
public class EmployeeTypeController {


	@Autowired private EmployeeTypeService employeeTypeService;

	@GetMapping("/")
	public List<EmployeeType> findAll(){
		return employeeTypeService.findAll();
	}

	@GetMapping("/findById/{id}")
	public Optional<EmployeeType> findById(@PathVariable Integer id) {
		return employeeTypeService.findById(id);
	}

	@PostMapping("/add")
	public ResponseEntity<EmployeeType> addNew(@RequestBody EmployeeType employeeType) {
		EmployeeType savedEmployeeType = employeeTypeService.save(employeeType);
		return new ResponseEntity<>(savedEmployeeType, HttpStatus.CREATED);
	}

	@PutMapping("/update/{id}")
	public EmployeeType update(@PathVariable Integer id,@RequestBody EmployeeType employeeType) {
		employeeType.setId(id);
		return employeeTypeService.save(employeeType);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		employeeTypeService.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
