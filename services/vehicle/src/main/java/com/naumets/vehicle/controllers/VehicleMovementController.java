package com.naumets.vehicle.controllers;

import java.util.List;
import java.util.Optional;

import com.naumets.vehicle.models.VehicleMovement;
import com.naumets.vehicle.services.VehicleMovementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/vehicle/vehicleMovements")
public class VehicleMovementController {


	@Autowired private VehicleMovementService vehicleMovementService;

	@GetMapping("/")
	public List<VehicleMovement> findAll(){
		return vehicleMovementService.findAll();
	}

	@GetMapping("/findById/{id}")
	public Optional<VehicleMovement> findById(@PathVariable Integer id) {
		return vehicleMovementService.findById(id);
	}

	@PostMapping("/add")
	public ResponseEntity<VehicleMovement> addNew(@RequestBody VehicleMovement vehicleMovement) {
		VehicleMovement savedVehicle = vehicleMovementService.save(vehicleMovement);
		return new ResponseEntity<>(savedVehicle, HttpStatus.CREATED);
	}

	@PutMapping("/update")
	public VehicleMovement update(@RequestBody VehicleMovement vehicleMovement) {
		return vehicleMovementService.save(vehicleMovement);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		vehicleMovementService.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
