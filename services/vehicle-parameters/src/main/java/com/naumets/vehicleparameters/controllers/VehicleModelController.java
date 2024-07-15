package com.naumets.vehicleparameters.controllers;

import java.util.List;
import java.util.Optional;

import com.naumets.vehicleparameters.models.VehicleModel;
import com.naumets.vehicleparameters.services.VehicleModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/vehicleParameters/vehicleModel")
public class VehicleModelController {

	
	@Autowired private VehicleModelService vehicleModelService;
	
	@GetMapping("/")
	public List<VehicleModel> findAll(){		
		return vehicleModelService.findAll();
	}

	@GetMapping("/findById/{id}")
	public Optional<VehicleModel> findById(@PathVariable Integer id) {
		return vehicleModelService.findById(id);
	}

	@PostMapping("/add")
	public ResponseEntity<VehicleModel> addNew(@RequestBody VehicleModel vehicleModel) {
		VehicleModel savedVehicleModel = vehicleModelService.save(vehicleModel);
		return new ResponseEntity<>(savedVehicleModel, HttpStatus.CREATED);
	}

	@PutMapping("/update")
	public VehicleModel update(@RequestBody VehicleModel vehicleModel) {
		return vehicleModelService.save(vehicleModel);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		vehicleModelService.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
