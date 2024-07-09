package com.naumets.community.controllers;

import java.util.List;
import java.util.Optional;

import com.naumets.community.models.locations.Country;
import com.naumets.community.models.locations.State;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.naumets.community.models.Client;
import com.naumets.community.services.ClientService;

@RestController
@RequestMapping("/api/community/clients")
public class ClientController {

	@Autowired
	private ClientService clientService;


	@GetMapping("/")
	public List<Client> findAll() {
		System.out.println("----------------------------------> ");
		return clientService.findAll();
	}

	@GetMapping("/findById/{id}")
	public Optional<Client> findById(@PathVariable Integer id) {
		return clientService.findById(id);
	}

	@PostMapping("/add")
	public Client addNew(@RequestBody Client client) {
		return clientService.save(client);
	}

	@PutMapping("/update/")
	public Client update( @RequestBody Client client) {
		return clientService.save(client);
	}

	@DeleteMapping("/delete/{id}")
	public void delete(@PathVariable Integer id) {
		clientService.delete(id);
	}

}
