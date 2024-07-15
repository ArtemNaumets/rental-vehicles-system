package com.naumets.vehicle.controllers;

import java.util.List;
import java.util.Optional;

import com.naumets.vehicle.models.Vehicle;
import com.naumets.vehicle.services.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/vehicle/vehicles")
public class VehicleController {


	@Autowired private VehicleService vehicleService;

	@GetMapping("/")
	public List<Vehicle> findAll(){
		return vehicleService.findAll();
	}

	@GetMapping("/findById/{id}")
	public Optional<Vehicle> findById(@PathVariable Integer id) {
		return vehicleService.findById(id);
	}

	@PostMapping("/add")
	public ResponseEntity<Vehicle> addNew(@RequestBody Vehicle vehicle) {
		Vehicle savedVehicle = vehicleService.save(vehicle);
		return new ResponseEntity<>(savedVehicle, HttpStatus.CREATED);
	}

	@PutMapping("/update")
	public Vehicle update(@RequestBody Vehicle vehicle) {
		return vehicleService.save(vehicle);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		vehicleService.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
