package com.naumets.vehicle.services;

import java.util.List;
import java.util.Optional;

import com.naumets.vehicle.models.VehicleMovement;
import com.naumets.vehicle.repositories.VehicleMovementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;


@Service
public class VehicleMovementService {
	
	@Autowired
	private VehicleMovementRepository vehicleMovementRepository;
	
	public List<VehicleMovement> findAll(){
		return vehicleMovementRepository.findAll();
	}

	@Cacheable(value = "vehicleMovementById", key = "#id")
	public Optional<VehicleMovement> findById(int id) {
		return vehicleMovementRepository.findById(id);
	}

	@CacheEvict(value = "vehicleMovementById", allEntries = true)
	public void delete(int id) {
		vehicleMovementRepository.deleteById(id);
	}

	@CachePut(value = "vehicleMovementById", key = "#vehicleMovement.id")
	public VehicleMovement save(VehicleMovement vehicleMovement) {
		vehicleMovementRepository.save(vehicleMovement);
        return vehicleMovement;
    }

}
