package com.naumets.vehicleparameters.services;

import java.util.List;
import java.util.Optional;

import com.naumets.vehicleparameters.models.VehicleType;
import com.naumets.vehicleparameters.repositories.VehicleTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class VehicleTypeService {
	
	@Autowired
	private VehicleTypeRepository vehicleTypeRepository;
	
	public List<VehicleType> findAll(){
		return vehicleTypeRepository.findAll();
	}	
	
	public Optional<VehicleType> findById(int id) {
		return vehicleTypeRepository.findById(id);
	}	
	
	public void delete(int id) {
		vehicleTypeRepository.deleteById(id);
	}
	
	public VehicleType save(VehicleType vehicleType) {
		vehicleTypeRepository.save(vehicleType);
        return vehicleType;
    }

}
