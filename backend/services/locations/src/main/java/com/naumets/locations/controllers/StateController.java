package com.naumets.locations.controllers;

import java.util.List;
import java.util.Optional;

import com.naumets.locations.models.State;
import com.naumets.locations.services.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/locations/states")
public class StateController {


	@Autowired private StateService stateService;

	@GetMapping("/")
	public List<State> findAll(){
		return stateService.findAll();
	}

	@GetMapping("/findById/{id}")
	public Optional<State> findById(@PathVariable Integer id) {
		return stateService.findById(id);
	}

	@PostMapping("/add")
	public ResponseEntity<State> addNew(@RequestBody State state) {
		State savedState = stateService.save(state);
		return new ResponseEntity<>(savedState, HttpStatus.CREATED);
	}

	@PutMapping("/update/{id}")
	public State update(@PathVariable Integer id,@RequestBody State state) {
		state.setId(id);
		return stateService.save(state);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		stateService.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}