package com.naumets.locations.services;

import java.util.List;
import java.util.Optional;

import com.naumets.locations.models.Location;
import com.naumets.locations.repositories.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class LocationService {
	
	@Autowired
	private LocationRepository locationRepository;

	public List<Location> findAll() {
		return (List<Location>) locationRepository.findAll();
	}

	public Optional<Location> findById(Integer id) {
		return locationRepository.findById(id);
	}
	
	public Location save(Location location) {
		locationRepository.save(location);
		return location;
	}
	
	public void delete(Integer id) {
		locationRepository.deleteById(id);
	}

	public Integer countAll() {
		return locationRepository.countAllBy();
	}
}
