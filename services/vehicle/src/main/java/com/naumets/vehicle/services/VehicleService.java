package com.naumets.vehicle.services;

import java.util.*;

import com.naumets.vehicle.dtos.VehicleTypeDTO;
import com.naumets.vehicle.feignClient.VehicleTypeClient;
import com.naumets.vehicle.models.Vehicle;
import com.naumets.vehicle.repositories.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;


@Service
public class VehicleService {

	@Autowired
	private VehicleRepository vehicleRepository;


	@Autowired
	private VehicleTypeClient vehicleTypeClient;


	public List<Vehicle> findAll() {
		return vehicleRepository.findAll();
	}

	@Cacheable(value = "vehicleById", key = "#id")
	public Optional<Vehicle> findById(int id) {
		return vehicleRepository.findById(id);
	}

	@CacheEvict(value = "vehicleById", allEntries = true)
	public void delete(int id) {
		vehicleRepository.deleteById(id);
	}

	@CachePut(value = "vehicleById", key = "#vehicle.id")
	public Vehicle save(Vehicle vehicle) {
		return vehicleRepository.save(vehicle);
	}

//	public Map<String, Integer> countVehiclesByTypes(List<String> types) {
//		Map<String, Integer> countMap = new HashMap<>();
//
//		for (String type : types) {
//			VehicleTypeDTO vehicleTypeDTO = vehicleTypeClient.getVehicleTypeByDescription(type);
//			if (vehicleTypeDTO != null) {
//				countMap.put(type, vehicleRepository.countVehiclesByTypeId(vehicleTypeDTO.getId()));
//			} else {
//				countMap.put(type, 0);
//			}
//		}
//
//		return countMap;
//	}
}