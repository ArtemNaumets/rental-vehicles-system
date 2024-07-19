package com.naumets.vehicle.services;

import java.util.List;
import java.util.Optional;

import com.naumets.vehicle.dtos.ClientDTO;
import com.naumets.vehicle.feignClient.CommunityServiceClient;
import com.naumets.vehicle.kafka.HireConfirmation;
import com.naumets.vehicle.kafka.VehicleHireProducer;
import com.naumets.vehicle.mappers.VehicleMapper;
import com.naumets.vehicle.models.VehicleHire;
import com.naumets.vehicle.repositories.VehicleHireRepository;
import com.naumets.vehicle.repositories.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;


@Service
public class VehicleHireService {
	
	@Autowired
	private VehicleHireRepository vehicleHireRepository;

	@Autowired
	private VehicleHireProducer vehicleHireProducer;

	@Autowired
	private CommunityServiceClient communityServiceClient;

	@Autowired
	private VehicleRepository vehicleRepository;

	public List<VehicleHire> findAll(){
		return vehicleHireRepository.findAll();
	}

	@Cacheable(value = "vehicleHireById", key = "#id")
	public Optional<VehicleHire> findById(int id) {
		return vehicleHireRepository.findById(id);
	}

	@CacheEvict(value = "vehicleHireById", allEntries = true)
	public void delete(int id) {
		vehicleHireRepository.deleteById(id);
	}

	@CachePut(value = "vehicleHireById", key = "#vehicleHire.id")
	public VehicleHire save(VehicleHire vehicleHire) {
		vehicleHire.setVehicle(vehicleRepository.findById(vehicleHire.getVehicleid()).get());
		ClientDTO clientDTO = communityServiceClient.getClientDTOById(vehicleHire.getClientid());
		vehicleHireRepository.save(vehicleHire);
		vehicleHireProducer.sendHireConfirmation(new HireConfirmation(
				clientDTO,
				vehicleHire.getDateIn(),
				vehicleHire.getDateOut(),
				vehicleHire.getPrice(),
				VehicleMapper.toDTO(vehicleHire.getVehicle())
		));
        return vehicleHire;
    }

	public List<VehicleHire> findLast10(){
		return vehicleHireRepository.findLast10();
	}
}
