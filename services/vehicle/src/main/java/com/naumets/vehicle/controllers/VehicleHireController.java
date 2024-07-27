package com.naumets.vehicle.controllers;

import java.util.List;
import java.util.Optional;

import com.naumets.vehicle.models.VehicleHire;
import com.naumets.vehicle.services.VehicleHireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/vehicle/vehicleHires")
public class VehicleHireController {


	@Autowired private VehicleHireService vehicleHireService;

	@GetMapping("/")
	public List<VehicleHire> findAll(){
		return vehicleHireService.findAll();
	}

	@GetMapping("/findById/{id}")
	public Optional<VehicleHire> findById(@PathVariable Integer id) {
		return vehicleHireService.findById(id);
	}

	@PostMapping("/add")
	public ResponseEntity<VehicleHire> addNew(@RequestBody VehicleHire vehicleHire) {
		VehicleHire savedVehicle = vehicleHireService.save(vehicleHire);
		return new ResponseEntity<>(savedVehicle, HttpStatus.CREATED);
	}

	@PutMapping("/update/{id}")
	public VehicleHire update(@PathVariable Integer id,@RequestBody VehicleHire vehicleHire) {
		vehicleHire.setId(id);
		return vehicleHireService.save(vehicleHire);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		vehicleHireService.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
