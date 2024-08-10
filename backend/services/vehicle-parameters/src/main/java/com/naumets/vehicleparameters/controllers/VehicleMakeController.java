package com.naumets.vehicleparameters.controllers;

import java.util.List;
import java.util.Optional;

import com.naumets.vehicleparameters.models.VehicleMake;
import com.naumets.vehicleparameters.services.VehicleMakeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/vehicleParameters/vehicleMakes")
public class VehicleMakeController {
	
	@Autowired private VehicleMakeService vehicleMakeService;
	
	@GetMapping("/")
	public ResponseEntity<List<VehicleMake>> findAll(){
		return new ResponseEntity<>(vehicleMakeService.findAll(), HttpStatus.OK);
	}	
	
	@GetMapping("/findById/{id}")
	public ResponseEntity<Optional<VehicleMake>> findById(@PathVariable Integer id) {
		return new ResponseEntity<>(vehicleMakeService.findById(id), HttpStatus.OK);
	}

	@PostMapping("/add")
	public ResponseEntity<VehicleMake> addNew(@RequestBody VehicleMake vehicleMake) {
		VehicleMake savedVehicleMake = vehicleMakeService.save(vehicleMake);
		return new ResponseEntity<>(savedVehicleMake, HttpStatus.CREATED);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<VehicleMake> update(@RequestBody VehicleMake vehicleMake, @PathVariable Integer id) {
		vehicleMake.setId(id);
		VehicleMake updatedVehicleMake = vehicleMakeService.save(vehicleMake);
		return new ResponseEntity<>(updatedVehicleMake, HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		vehicleMakeService.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}


}
