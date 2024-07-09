package com.naumets.finance.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.naumets.finance.models.Client;
import com.naumets.finance.models.Invoice;
import com.naumets.finance.services.InvoiceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class InvoiceControllerTests {

    private MockMvc mockMvc;

    @Mock
    private InvoiceService invoiceService;

    @InjectMocks
    private InvoiceController invoiceController;

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(invoiceController).build();
    }

    @Test
    void findAll_shouldReturnListOfInvoices() throws Exception {
        List<Invoice> invoices = Arrays.asList(
                new Invoice(1, new Date(), new Client(), 1, "Invoice 1"),
                new Invoice(2, new Date(), new Client(), 2, "Invoice 2")
        );
        given(invoiceService.findAll()).willReturn(invoices);

        mockMvc.perform(get("/api/finance/invoices/"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].remarks").value("Invoice 1"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].remarks").value("Invoice 2"));

        verify(invoiceService).findAll();
        verifyNoMoreInteractions(invoiceService);
    }

    @Test
    void findById_shouldReturnInvoiceIfExists() throws Exception {
        Invoice invoice = new Invoice(1, new Date(), new Client(), 1, "Invoice 1");
        given(invoiceService.findById(1)).willReturn(Optional.of(invoice));

        mockMvc.perform(get("/api/finance/invoices/findById/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.remarks").value("Invoice 1"));

        verify(invoiceService).findById(1);
        verifyNoMoreInteractions(invoiceService);
    }

    @Test
    void addNew_shouldCreateNewInvoice() throws Exception {
        Invoice invoice = new Invoice(null, new Date(), new Client(), 1, "New Invoice");

        given(invoiceService.save(any(Invoice.class))).willReturn(invoice);

        mockMvc.perform(post("/api/finance/invoices/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invoice)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.remarks").value("New Invoice"));

        verify(invoiceService).save(any(Invoice.class));
        verifyNoMoreInteractions(invoiceService);
    }

    @Test
    void update_shouldUpdateInvoice() throws Exception {
        Invoice invoice = new Invoice(1, new Date(), new Client(), 1, "Updated Invoice");

        given(invoiceService.save(any(Invoice.class))).willReturn(invoice);

        mockMvc.perform(put("/api/finance/invoices/update/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invoice)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.remarks").value("Updated Invoice"));

        verify(invoiceService).save(any(Invoice.class));
        verifyNoMoreInteractions(invoiceService);
    }

    @Test
    void delete_shouldDeleteInvoice() throws Exception {
        mockMvc.perform(delete("/api/finance/invoices/delete/1"))
                .andExpect(status().isNoContent());

        verify(invoiceService).delete(1);
        verifyNoMoreInteractions(invoiceService);
    }
}
