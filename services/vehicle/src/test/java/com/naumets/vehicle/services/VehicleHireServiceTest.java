package com.naumets.vehicle.services;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.naumets.vehicle.models.VehicleHire;
import com.naumets.vehicle.repositories.VehicleHireRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


public class VehicleHireServiceTest {

    @Mock
    private VehicleHireRepository vehicleHireRepository;

    @InjectMocks
    private VehicleHireService vehicleHireService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void VehicleHireService_findAll_shouldReturnAllVehicleHires() {
        VehicleHire vehicleHire1 = new VehicleHire();
        VehicleHire vehicleHire2 = new VehicleHire();
        List<VehicleHire> vehicleHires = Arrays.asList(vehicleHire1, vehicleHire2);
        when(vehicleHireRepository.findAll()).thenReturn(vehicleHires);

        List<VehicleHire> result = vehicleHireService.findAll();

        assertEquals(2, result.size());
        assertEquals(vehicleHires, result);
        verify(vehicleHireRepository, times(1)).findAll();
    }

    @Test
    public void VehicleHireService_findById_shouldReturnVehicleHireById() {
        VehicleHire vehicleHire = new VehicleHire();
        when(vehicleHireRepository.findById(1)).thenReturn(Optional.of(vehicleHire));

        Optional<VehicleHire> result = vehicleHireService.findById(1);

        assertTrue(result.isPresent());
        assertEquals(vehicleHire, result.get());
        verify(vehicleHireRepository, times(1)).findById(1);
    }


    @Test
    public void VehicleHireService_delete_shouldDeleteVehicleHireById() {
        vehicleHireService.delete(1);

        verify(vehicleHireRepository, times(1)).deleteById(1);
    }

    @Test
    public void VehicleHireService_findLast10_shouldReturnLast10VehicleHires() {
        VehicleHire vehicleHire1 = new VehicleHire();
        VehicleHire vehicleHire2 = new VehicleHire();
        List<VehicleHire> vehicleHires = Arrays.asList(vehicleHire1, vehicleHire2);
        when(vehicleHireRepository.findLast10()).thenReturn(vehicleHires);

        List<VehicleHire> result = vehicleHireService.findLast10();

        assertEquals(vehicleHires.size(), result.size());
        assertEquals(vehicleHires, result);
        verify(vehicleHireRepository, times(1)).findLast10();
    }
}
