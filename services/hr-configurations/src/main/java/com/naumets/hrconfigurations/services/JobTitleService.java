package com.naumets.hrconfigurations.services;

import java.util.List;
import java.util.Optional;

import com.naumets.hrconfigurations.models.JobTitle;
import com.naumets.hrconfigurations.repositories.JobTitleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class JobTitleService {

	@Autowired
	private JobTitleRepository jobTitleRepository;
	
	public List<JobTitle> findAll(){
		return jobTitleRepository.findAll();
	}	
	
	public Optional<JobTitle> findById(int id) {
		return jobTitleRepository.findById(id);
	}	
	
	public void delete(int id) {
		jobTitleRepository.deleteById(id);
	}
	
	public JobTitle save(JobTitle jobTitle) {
		jobTitleRepository.save(jobTitle);
		return jobTitle;
	}
}
