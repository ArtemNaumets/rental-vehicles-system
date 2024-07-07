package com.naumets.vehiclemanagementservice.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.naumets.vehiclemanagementservice.models.vehicle.VehicleHire;
import com.naumets.vehiclemanagementservice.repositories.vehicle.VehicleHireRepository;

@Service
public class VehicleHireService {
	
	@Autowired
	private VehicleHireRepository vehicleHireRepository;
	
	public List<VehicleHire> findAll(){
		return vehicleHireRepository.findAll();
	}	
	
	public Optional<VehicleHire> findById(int id) {
		return vehicleHireRepository.findById(id);
	}	
	
	public void delete(int id) {
		vehicleHireRepository.deleteById(id);
	}
	
	public void save(VehicleHire vehicleHire) {
		vehicleHireRepository.save(vehicleHire);
	}

	public List<VehicleHire> findLast10(){
		return vehicleHireRepository.findLast10();
	}
}
