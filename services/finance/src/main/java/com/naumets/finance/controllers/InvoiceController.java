package com.naumets.finance.controllers;

import java.util.List;
import java.util.Optional;

import com.naumets.finance.models.Invoice;
import com.naumets.finance.services.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/finance/invoices")
public class InvoiceController {

	@Autowired private InvoiceService invoiceService;

	@GetMapping("/")
	public ResponseEntity<List<Invoice>> findAll() {
		return ResponseEntity.ok(invoiceService.findAll());
	}

	@GetMapping("/findById/{id}")
	public ResponseEntity<Optional<Invoice>> findById(@PathVariable Integer id) {
		Optional<Invoice> invoice = invoiceService.findById(id);
		return invoice.isPresent() ? ResponseEntity.ok(invoice) : ResponseEntity.notFound().build();
	}

	@PostMapping("/add")
	public ResponseEntity<Invoice> addNew(@RequestBody Invoice invoice) {
		Invoice savedInvoice = invoiceService.save(invoice);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedInvoice);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<Invoice> update(@PathVariable Integer id, @RequestBody Invoice invoice) {
		invoice.setId(id);
		Invoice updatedInvoice = invoiceService.save(invoice);
		return ResponseEntity.ok(updatedInvoice);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		invoiceService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
