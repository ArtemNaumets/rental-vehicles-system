package com.naumets.finance.services;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.naumets.finance.models.Invoice;
import com.naumets.finance.repositories.InvoiceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


public class InvoiceServiceTest {

    @Mock
    private InvoiceRepository invoiceRepository;

    @InjectMocks
    private InvoiceService invoiceService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void InvoiceService_findAll_shouldReturnAllInvoices() {
        Invoice invoice1 = new Invoice();
        Invoice invoice2 = new Invoice();
        List<Invoice> invoices = Arrays.asList(invoice1, invoice2);
        when(invoiceRepository.findAll()).thenReturn(invoices);

        List<Invoice> result = invoiceService.findAll();

        assertEquals(2, result.size());
        assertEquals(invoices, result);
        verify(invoiceRepository, times(1)).findAll();
    }

    @Test
    public void InvoiceService_findById_shouldReturnInvoiceById() {
        Invoice invoice = new Invoice();
        when(invoiceRepository.findById(1)).thenReturn(Optional.of(invoice));

        Optional<Invoice> result = invoiceService.findById(1);

        assertTrue(result.isPresent());
        assertEquals(invoice, result.get());
        verify(invoiceRepository, times(1)).findById(1);
    }

    @Test
    public void InvoiceService_delete_shouldDeleteInvoiceById() {
        invoiceService.delete(1);

        verify(invoiceRepository, times(1)).deleteById(1);
    }

    @Test
    public void InvoiceService_save_shouldSaveInvoice() {
        Invoice invoice = new Invoice();

        invoiceService.save(invoice);

        verify(invoiceRepository, times(1)).save(invoice);
    }
}
