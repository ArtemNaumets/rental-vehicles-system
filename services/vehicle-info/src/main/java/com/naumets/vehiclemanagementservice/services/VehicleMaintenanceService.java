package com.naumets.vehiclemanagementservice.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.naumets.vehiclemanagementservice.models.vehicle.VehicleMaintenance;
import com.naumets.vehiclemanagementservice.repositories.vehicle.VehicleMaintenanceRepository;

@Service
public class VehicleMaintenanceService {

	@Autowired
	private VehicleMaintenanceRepository vehicleMaintenanceRepository;
	
	public List<VehicleMaintenance> findAll(){
		return vehicleMaintenanceRepository.findAll();
	}	
	
	public Optional<VehicleMaintenance> findById(int id) {
		return vehicleMaintenanceRepository.findById(id);
	}	
	
	public void delete(int id) {
		vehicleMaintenanceRepository.deleteById(id);
	}
	
	public void save(VehicleMaintenance vehicleMaintenance) {
		vehicleMaintenanceRepository.save(vehicleMaintenance);
	}

	public List<VehicleMaintenance> findLast10() {
		return vehicleMaintenanceRepository.findLast10();
	}
}
