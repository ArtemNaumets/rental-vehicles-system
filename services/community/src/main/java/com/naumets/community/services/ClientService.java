package com.naumets.community.services;

import java.util.List;
import java.util.Optional;

import com.naumets.community.models.Client;
import com.naumets.community.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

	@Autowired
	private ClientRepository clientRepository;

	public List<Client> findAll() {
		return clientRepository.findAll();
	}

	public Optional<Client> findById(String id) {
		return clientRepository.findById(id);
	}

	public void delete(String id) {
		clientRepository.deleteById(id);
	}

	public Client save(Client client) {
		return clientRepository.save(client);
	}
}
