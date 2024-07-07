package com.naumets.locations.services;

import java.util.List;
import java.util.Optional;

import com.naumets.locations.models.State;
import com.naumets.locations.repositories.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class StateService {

	@Autowired
	private StateRepository stateRepository;
	
	public List<State> findAll(){
		return stateRepository.findAll();
	}	
	
	public Optional<State> findById(int id) {
		return stateRepository.findById(id);
	}	
	
	public void delete(int id) {
		stateRepository.deleteById(id);
	}
	
	public State save(State state) {
		stateRepository.save(state);
		return state;
	}
}
