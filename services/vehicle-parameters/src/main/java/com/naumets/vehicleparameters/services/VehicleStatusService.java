package com.naumets.vehicleparameters.services;

import java.util.List;
import java.util.Optional;

import com.naumets.vehicleparameters.models.VehicleStatus;
import com.naumets.vehicleparameters.repositories.VehicleStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class VehicleStatusService {
	
	@Autowired
	private VehicleStatusRepository vehicleStatusRepository;
	
	public List<VehicleStatus> findAll(){
		return vehicleStatusRepository.findAll();
	}	
	
	public Optional<VehicleStatus> findById(int id) {
		return vehicleStatusRepository.findById(id);
	}	
	
	public void delete(int id) {
		vehicleStatusRepository.deleteById(id);
	}
	
	public VehicleStatus save(VehicleStatus vehicleStatus) {
		vehicleStatusRepository.save(vehicleStatus);
        return vehicleStatus;
    }

}
