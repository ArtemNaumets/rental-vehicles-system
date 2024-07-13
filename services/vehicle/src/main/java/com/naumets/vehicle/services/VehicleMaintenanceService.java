package com.naumets.vehicle.services;

import java.util.List;
import java.util.Optional;

import com.naumets.vehicle.models.VehicleMaintenance;
import com.naumets.vehicle.repositories.VehicleMaintenanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;


@Service
public class VehicleMaintenanceService {

	@Autowired
	private VehicleMaintenanceRepository vehicleMaintenanceRepository;
	
	public List<VehicleMaintenance> findAll(){
		return vehicleMaintenanceRepository.findAll();
	}

	@Cacheable(value = "vehicleMaintenanceById", key = "#id")
	public Optional<VehicleMaintenance> findById(int id) {
		return vehicleMaintenanceRepository.findById(id);
	}

	@CacheEvict(value = "vehicleMaintenanceById", allEntries = true)
	public void delete(int id) {
		vehicleMaintenanceRepository.deleteById(id);
	}

	@CachePut(value = "vehicleMaintenanceById", key = "#vehicleMaintenance.id")
	public VehicleMaintenance save(VehicleMaintenance vehicleMaintenance) {
		vehicleMaintenanceRepository.save(vehicleMaintenance);
        return vehicleMaintenance;
    }

	public List<VehicleMaintenance> findLast10() {
		return vehicleMaintenanceRepository.findLast10();
	}
}
