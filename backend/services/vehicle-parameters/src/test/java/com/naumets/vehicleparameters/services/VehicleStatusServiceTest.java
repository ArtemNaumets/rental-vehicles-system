package com.naumets.vehicleparameters.services;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

import com.naumets.vehicleparameters.models.VehicleStatus;
import com.naumets.vehicleparameters.repositories.VehicleStatusRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


public class VehicleStatusServiceTest {

    @Mock
    private VehicleStatusRepository vehicleStatusRepository;

    @InjectMocks
    private VehicleStatusService vehicleStatusService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void VehicleStatusService_findAll_shouldReturnAllVehicleStatuses() {
        VehicleStatus vehicleStatus1 = new VehicleStatus();
        VehicleStatus vehicleStatus2 = new VehicleStatus();
        List<VehicleStatus> vehicleStatuses = Arrays.asList(vehicleStatus1, vehicleStatus2);
        when(vehicleStatusRepository.findAll()).thenReturn(vehicleStatuses);

        List<VehicleStatus> result = vehicleStatusService.findAll();

        assertEquals(2, result.size());
        assertEquals(vehicleStatuses, result);
        verify(vehicleStatusRepository, times(1)).findAll();
    }

    @Test
    public void VehicleStatusService_findById_shouldReturnVehicleStatusById() {
        VehicleStatus vehicleStatus = new VehicleStatus();
        when(vehicleStatusRepository.findById(1)).thenReturn(Optional.of(vehicleStatus));

        Optional<VehicleStatus> result = vehicleStatusService.findById(1);

        assertTrue(result.isPresent());
        assertEquals(vehicleStatus, result.get());
        verify(vehicleStatusRepository, times(1)).findById(1);
    }

    @Test
    public void VehicleStatusService_save_shouldSaveVehicleStatus() {
        VehicleStatus vehicleStatus = new VehicleStatus();

        vehicleStatusService.save(vehicleStatus);

        verify(vehicleStatusRepository, times(1)).save(vehicleStatus);
    }

    @Test
    public void VehicleStatusService_delete_shouldDeleteVehicleStatusById() {
        vehicleStatusService.delete(1);

        verify(vehicleStatusRepository, times(1)).deleteById(1);
    }
}
