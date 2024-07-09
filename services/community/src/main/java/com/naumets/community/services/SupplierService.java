package com.naumets.community.services;

import java.util.List;
import java.util.Optional;

import com.naumets.community.models.Supplier;
import com.naumets.community.repositories.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class SupplierService {
	@Autowired
	private SupplierRepository supplierRepository;
	
	public List<Supplier> findAll(){
		return supplierRepository.findAll();
	}	
	
	public Optional<Supplier> findById(int id) {
		return supplierRepository.findById(id);
	}	
	
	public void delete(int id) {
		supplierRepository.deleteById(id);
	}
	
	public Supplier save(Supplier supplier) {
		supplierRepository.save(supplier);
        return supplier;
    }

}
