package com.naumets.vehicle.controllers;

import java.util.List;
import java.util.Optional;

import com.naumets.vehicle.models.VehicleMaintenance;
import com.naumets.vehicle.services.VehicleMaintenanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/vehicle/vehicleMaintenances")
public class VehicleMaintenanceController {


	@Autowired private VehicleMaintenanceService vehicleMaintenanceService;

	@GetMapping("/")
	public List<VehicleMaintenance> findAll(){
		return vehicleMaintenanceService.findAll();
	}

	@GetMapping("/findById/{id}")
	public Optional<VehicleMaintenance> findById(@PathVariable Integer id) {
		return vehicleMaintenanceService.findById(id);
	}

	@PostMapping("/add")
	public ResponseEntity<VehicleMaintenance> addNew(@RequestBody VehicleMaintenance vehicleMaintenance) {
		VehicleMaintenance savedVehicle = vehicleMaintenanceService.save(vehicleMaintenance);
		return new ResponseEntity<>(savedVehicle, HttpStatus.CREATED);
	}

	@PutMapping("/update")
	public VehicleMaintenance update(@RequestBody VehicleMaintenance vehicleMaintenance) {
		return vehicleMaintenanceService.save(vehicleMaintenance);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		vehicleMaintenanceService.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
