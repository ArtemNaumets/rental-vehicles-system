package com.naumets.finance.services;

import java.util.List;
import java.util.Optional;

import com.naumets.finance.models.Invoice;
import com.naumets.finance.repositories.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InvoiceService {
	
	@Autowired
	private InvoiceRepository invoiceRepository;
	
	public List<Invoice> findAll(){
		return invoiceRepository.findAll();
	}	
	
	public Optional<Invoice> findById(int id) {
		return invoiceRepository.findById(id);
	}	
	
	public void delete(int id) {
		invoiceRepository.deleteById(id);
	}
	
	public Invoice save(Invoice invoice) {
		invoiceRepository.save(invoice);
		return invoice;
	}

}
