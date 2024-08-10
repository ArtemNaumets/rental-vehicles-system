package com.naumets.vehicleparameters.services;

import java.util.List;
import java.util.Optional;

import com.naumets.vehicleparameters.models.VehicleMake;
import com.naumets.vehicleparameters.repositories.VehicleMakeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class VehicleMakeService {
	
	@Autowired
	private VehicleMakeRepository vehicleMakeRepository;
	
	public List<VehicleMake> findAll(){
		return vehicleMakeRepository.findAll();
	}	
	
	public Optional<VehicleMake> findById(int id) {
		return vehicleMakeRepository.findById(id);
	}	
	
	public void delete(int id) {
		vehicleMakeRepository.deleteById(id);
	}
	
	public VehicleMake save(VehicleMake vehicleMake) {
			vehicleMakeRepository.save(vehicleMake);
        return vehicleMake;
    }

}
