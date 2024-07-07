package com.naumets.vehicleparameters.services;

import java.util.List;
import java.util.Optional;

import com.naumets.vehicleparameters.models.VehicleModel;
import com.naumets.vehicleparameters.repositories.VehicleModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class VehicleModelService {
	
	@Autowired
	private VehicleModelRepository vehicleModelRepository;
	
	public List<VehicleModel> findAll(){
		return vehicleModelRepository.findAll();
	}


	public Optional<VehicleModel> findById(int id) {
		return vehicleModelRepository.findById(id);
	}	
	
	public void delete(int id) {
		vehicleModelRepository.deleteById(id);
	}
	
	public VehicleModel save(VehicleModel vehicleModel) {
		vehicleModelRepository.save(vehicleModel);
		return vehicleModel;
	}

}
