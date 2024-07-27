package com.naumets.community.controllers;

import java.util.List;
import java.util.Optional;

import com.naumets.community.dtos.ClientDTO;
import com.naumets.community.mappers.ClientMapper;
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
		return clientService.findAll();
	}

	@GetMapping("/findById/{id}")
	public Optional<ClientDTO> findById(@PathVariable String  id) {
		return Optional.of(ClientMapper.toDTO(clientService.findById(id).get()));
	}

	@PostMapping("/add")
	public Client addNew(@RequestBody Client client) {
		return clientService.save(client);
	}

	@PutMapping("/update/{id}")
	public Client update(@PathVariable String id, @RequestBody Client client) {
		client.setId(id);
		return clientService.save(client);
	}

	@DeleteMapping("/delete/{id}")
	public void delete(@PathVariable String  id) {
		clientService.delete(id);
	}

}
