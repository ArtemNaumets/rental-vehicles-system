package com.naumets.hrconfigurations.controllers;

import java.util.List;
import java.util.Optional;

import com.naumets.hrconfigurations.models.JobTitle;
import com.naumets.hrconfigurations.services.JobTitleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/hrConfigurations/jobTitles")
public class JobTitleController {


	@Autowired private JobTitleService jobTitleService;

	@GetMapping("/")
	public List<JobTitle> findAll(){
		return jobTitleService.findAll();
	}

	@GetMapping("/findById/{id}")
	public Optional<JobTitle> findById(@PathVariable Integer id) {
		return jobTitleService.findById(id);
	}

	@PostMapping("/add")
	public ResponseEntity<JobTitle> addNew(@RequestBody JobTitle jobTitle) {
		JobTitle savedJobTitle = jobTitleService.save(jobTitle);
		return new ResponseEntity<>(savedJobTitle, HttpStatus.CREATED);
	}

	@PutMapping("/update/{id}")
	public JobTitle update(@PathVariable Integer id,@RequestBody JobTitle jobTitle) {
		jobTitle.setId(id);
		return jobTitleService.save(jobTitle);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		jobTitleService.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
