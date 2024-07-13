package com.naumets.vehicle.services;

import java.util.List;
import java.util.Optional;

import com.naumets.vehicle.models.VehicleMaintenance;
import com.naumets.vehicle.repositories.VehicleMaintenanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


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
	
	public VehicleMaintenance save(VehicleMaintenance vehicleMaintenance) {
		vehicleMaintenanceRepository.save(vehicleMaintenance);
        return vehicleMaintenance;
    }

	public List<VehicleMaintenance> findLast10() {
		return vehicleMaintenanceRepository.findLast10();
	}
}
