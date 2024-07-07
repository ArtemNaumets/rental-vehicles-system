package com.naumets.vehiclemanagementservice.services;

import java.util.*;

import com.naumets.vehiclemanagementservice.models.vehicle.VehicleInformation;
import com.naumets.vehiclemanagementservice.repositories.vehicle.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class VehicleService {
	
	@Autowired
	private VehicleRepository vehicleRepository;
	
	public List<VehicleInformation> findAll(){
		return vehicleRepository.findAll();
	}	
	
	public Optional<VehicleInformation> findById(int id) {
		return vehicleRepository.findById(id);
	}	
	
	public void delete(int id) {
		vehicleRepository.deleteById(id);
	}
	
	public void save(VehicleInformation vehicleInformation) {
		vehicleRepository.save(vehicleInformation);
	}

    public Map<String, Integer> countVehiclesByTypes(List<String> types) {
		Map<String, Integer> newList = new HashMap<>();
		for (String type: types) {
			newList.put(type, vehicleRepository.countVehiclesByType(type));
		}
		 return newList;
    }
}
