package com.naumets.vehicle.services;

import java.util.*;

import com.naumets.vehicle.models.Vehicle;
import com.naumets.vehicle.repositories.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class VehicleService {
	
	@Autowired
	private VehicleRepository vehicleRepository;
	
	public List<Vehicle> findAll(){
		return vehicleRepository.findAll();
	}	
	
	public Optional<Vehicle> findById(int id) {
		return vehicleRepository.findById(id);
	}	
	
	public void delete(int id) {
		vehicleRepository.deleteById(id);
	}
	
	public Vehicle save(Vehicle vehicle) {
		vehicleRepository.save(vehicle);
		return vehicle;
	}

    public Map<String, Integer> countVehiclesByTypes(List<String> types) {
		Map<String, Integer> newList = new HashMap<>();
		for (String type: types) {
			newList.put(type, vehicleRepository.countVehiclesByType(type));
		}
		 return newList;
    }
}
