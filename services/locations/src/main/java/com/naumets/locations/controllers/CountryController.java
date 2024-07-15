package com.naumets.locations.controllers;

import java.util.List;
import java.util.Optional;

import com.naumets.locations.models.Country;
import com.naumets.locations.services.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/locations/countries")
public class CountryController {


	@Autowired private CountryService countryService;

	@GetMapping("/")
	public List<Country> findAll(){
		return countryService.findAll();
	}

	@GetMapping("/findById/{id}")
	public Optional<Country> findById(@PathVariable Integer id) {
		return countryService.findById(id);
	}

	@PostMapping("/add")
	public ResponseEntity<Country> addNew(@RequestBody Country country) {
		Country savedCountry = countryService.save(country);
		return new ResponseEntity<>(savedCountry, HttpStatus.CREATED);
	}

	@PutMapping("/update")
	public Country update(@RequestBody Country country) {
		return countryService.save(country);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		countryService.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}