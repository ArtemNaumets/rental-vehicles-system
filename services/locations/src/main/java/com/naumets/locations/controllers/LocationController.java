package com.naumets.locations.controllers;

import java.util.List;
import java.util.Optional;

import com.naumets.locations.models.Location;
import com.naumets.locations.services.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/locations/locations")
public class LocationController {


	@Autowired private LocationService locationService;

	@GetMapping("/")
	public List<Location> findAll(){
		return locationService.findAll();
	}

	@RequestMapping("/findById/{id}")
	public Optional<Location> findById(@PathVariable Integer id) {
		return locationService.findById(id);
	}

	@PostMapping("/add")
	public ResponseEntity<Location> addNew(@RequestBody Location location) {
		Location savedLocation = locationService.save(location);
		return new ResponseEntity<>(savedLocation, HttpStatus.CREATED);
	}

	@PutMapping("/update")
	public Location update(@RequestBody Location location) {
		return locationService.save(location);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		locationService.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}