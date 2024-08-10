package com.naumets.vehicleparameters.controllers;

import com.naumets.vehicleparameters.models.VehicleStatus;
import com.naumets.vehicleparameters.services.VehicleStatusService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class VehicleStatusControllerTest {

    private MockMvc mockMvc;

    @Mock
    private VehicleStatusService vehicleStatusService;

    @InjectMocks
    private VehicleStatusController vehicleStatusController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(vehicleStatusController).build();
    }

    @Test
    void testFindAll() {
        VehicleStatus vehicleStatus1 = new VehicleStatus();
        VehicleStatus vehicleStatus2 = new VehicleStatus();
        List<VehicleStatus> vehicleStatuses = Arrays.asList(vehicleStatus1, vehicleStatus2);

        when(vehicleStatusService.findAll()).thenReturn(vehicleStatuses);

        List<VehicleStatus> result = vehicleStatusController.findAll();

        assertEquals(2, result.size());
        verify(vehicleStatusService, times(1)).findAll();
    }

    @Test
    void testFindById() {
        VehicleStatus vehicleStatus = new VehicleStatus();
        when(vehicleStatusService.findById(1)).thenReturn(Optional.of(vehicleStatus));

        Optional<VehicleStatus> result = vehicleStatusController.findById(1);

        assertEquals(vehicleStatus, result.get());
        verify(vehicleStatusService, times(1)).findById(1);
    }

    @Test
    void testAddNew() {
        VehicleStatus vehicleStatus = new VehicleStatus();
        when(vehicleStatusService.save(any(VehicleStatus.class))).thenReturn(vehicleStatus);

        ResponseEntity<VehicleStatus> response = vehicleStatusController.addNew(vehicleStatus);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(vehicleStatus, response.getBody());
        verify(vehicleStatusService, times(1)).save(vehicleStatus);
    }

    @Test
    void testUpdate() {
        VehicleStatus vehicleStatus = new VehicleStatus();
        when(vehicleStatusService.save(any(VehicleStatus.class))).thenReturn(vehicleStatus);

        VehicleStatus result = vehicleStatusController.update(vehicleStatus,1);

        assertEquals(vehicleStatus, result);
        verify(vehicleStatusService, times(1)).save(vehicleStatus);
    }

    @Test
    void testDelete() {
        doNothing().when(vehicleStatusService).delete(1);

        ResponseEntity<Void> response = vehicleStatusController.delete(1);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(vehicleStatusService, times(1)).delete(1);
    }
}
