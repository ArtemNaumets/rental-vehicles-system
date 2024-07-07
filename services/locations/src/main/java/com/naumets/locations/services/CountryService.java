package com.naumets.locations.services;

import java.util.List;
import java.util.Optional;

import com.naumets.locations.models.Country;
import com.naumets.locations.repositories.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CountryService {
	
	@Autowired
	private CountryRepository countryRepository;
	
	public List<Country> findAll(){
		return countryRepository.findAll();
	}	
	
	public Optional<Country> findById(int id) {
		return countryRepository.findById(id);
	}	
	
	public void delete(int id) {
		countryRepository.deleteById(id);
	}
	
	public Country save(Country country) {
		countryRepository.save(country);
		return country;
	}

}
