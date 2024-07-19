package com.naumets.vehicleparameters.controllers;

import java.util.List;
import java.util.Optional;

import com.naumets.vehicleparameters.dtos.VehicleTypeDTO;
import com.naumets.vehicleparameters.mappers.VehicleTypeMapper;
import com.naumets.vehicleparameters.models.VehicleType;
import com.naumets.vehicleparameters.services.VehicleTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/vehicleParameters/vehicleType")
public class VehicleTypeController {


	@Autowired private VehicleTypeService vehicleTypeService;

	@GetMapping("/")
	public List<VehicleType> findAll(){
		return vehicleTypeService.findAll();
	}

	@GetMapping("/findById/{id}")
	public Optional<VehicleType> findById(@PathVariable Integer id) {
		return vehicleTypeService.findById(id);
	}

	@PostMapping("/add")
	public ResponseEntity<VehicleType> addNew(@RequestBody VehicleType vehicleType) {
		VehicleType savedVehicleType = vehicleTypeService.save(vehicleType);
		return new ResponseEntity<>(savedVehicleType, HttpStatus.CREATED);
	}

	@PutMapping("/update")
	public VehicleType update(@RequestBody VehicleType vehicleType) {
		return vehicleTypeService.save(vehicleType);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		vehicleTypeService.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}




}
