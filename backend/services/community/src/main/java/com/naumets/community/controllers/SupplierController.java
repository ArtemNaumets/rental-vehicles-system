package com.naumets.community.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.naumets.community.models.Supplier;
import com.naumets.community.services.SupplierService;

@RestController
@RequestMapping("/api/community/suppliers")
public class SupplierController {

	@Autowired private SupplierService supplierService;


	@GetMapping("/")
	public ResponseEntity<List<Supplier>> findAll() {
		return ResponseEntity.ok(supplierService.findAll());
	}

	@GetMapping("/findById/{id}")
	public ResponseEntity<Optional<Supplier>> findById(@PathVariable String id) {
		Optional<Supplier> supplier = supplierService.findById(id);
		return supplier.isPresent() ? ResponseEntity.ok(supplier) : ResponseEntity.notFound().build();
	}

	@PostMapping("/add")
	public ResponseEntity<Supplier> addNew(@RequestBody Supplier supplier) {
		Supplier savedSupplier = supplierService.save(supplier);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedSupplier);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<Supplier> update(@PathVariable String id, @RequestBody Supplier supplier) {
		supplier.setId(id);
		Supplier updatedSupplier = supplierService.save(supplier);
		return ResponseEntity.ok(updatedSupplier);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Void> delete(@PathVariable String id) {
		supplierService.delete(id);
		return ResponseEntity.noContent().build();
	}

}
