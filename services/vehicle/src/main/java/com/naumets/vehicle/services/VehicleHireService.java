package com.naumets.vehicle.services;

import java.util.List;
import java.util.Optional;

import com.naumets.vehicle.models.VehicleHire;
import com.naumets.vehicle.repositories.VehicleHireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


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
	
	public VehicleHire save(VehicleHire vehicleHire) {
		vehicleHireRepository.save(vehicleHire);
        return vehicleHire;
    }

	public List<VehicleHire> findLast10(){
		return vehicleHireRepository.findLast10();
	}
}
