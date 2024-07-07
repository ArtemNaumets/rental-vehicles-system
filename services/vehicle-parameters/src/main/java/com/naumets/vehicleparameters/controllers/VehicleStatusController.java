package com.naumets.vehicleparameters.controllers;

import java.util.List;
import java.util.Optional;

import com.naumets.vehicleparameters.models.VehicleStatus;
import com.naumets.vehicleparameters.services.VehicleStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/vehicleParameters/vehicleStatus")
public class VehicleStatusController {


	@Autowired private VehicleStatusService vehicleStatusService;

	@GetMapping("/")
	public List<VehicleStatus> findAll(){
		return vehicleStatusService.findAll();
	}

	@RequestMapping("/findById/{id}")
	public Optional<VehicleStatus> findById(@PathVariable Integer id) {
		return vehicleStatusService.findById(id);
	}

	@PostMapping("/add")
	public ResponseEntity<VehicleStatus> addNew(@RequestBody VehicleStatus vehicleStatus) {
		VehicleStatus savedVehicleStatus = vehicleStatusService.save(vehicleStatus);
		return new ResponseEntity<>(savedVehicleStatus, HttpStatus.CREATED);
	}

	@PutMapping("/update")
	public VehicleStatus update(@RequestBody VehicleStatus vehicleStatus) {
		return vehicleStatusService.save(vehicleStatus);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		vehicleStatusService.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}


}
