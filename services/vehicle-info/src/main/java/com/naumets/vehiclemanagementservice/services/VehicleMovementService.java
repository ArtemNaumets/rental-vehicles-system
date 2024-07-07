package com.naumets.vehiclemanagementservice.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.naumets.vehiclemanagementservice.models.vehicle.VehicleMovement;
import com.naumets.vehiclemanagementservice.repositories.vehicle.VehicleMovementRepository;

@Service
public class VehicleMovementService {
	
	@Autowired
	private VehicleMovementRepository vehicleMovementRepository;
	
	public List<VehicleMovement> findAll(){
		return vehicleMovementRepository.findAll();
	}	
	
	public Optional<VehicleMovement> findById(int id) {
		return vehicleMovementRepository.findById(id);
	}	
	
	public void delete(int id) {
		vehicleMovementRepository.deleteById(id);
	}
	
	public void save(VehicleMovement vehicleMovement) {
		vehicleMovementRepository.save(vehicleMovement);
	}

}
