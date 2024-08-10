package com.naumets.vehicleparameters.services;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

import com.naumets.vehicleparameters.models.VehicleMake;
import com.naumets.vehicleparameters.repositories.VehicleMakeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


public class VehicleMakeServiceTest {

    @Mock
    private VehicleMakeRepository vehicleMakeRepository;

    @InjectMocks
    private VehicleMakeService vehicleMakeService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void VehicleMakeService_findAll_shouldReturnAllVehicleMakes() {
        VehicleMake vehicleMake1 = new VehicleMake();
        VehicleMake vehicleMake2 = new VehicleMake();
        List<VehicleMake> vehicleMakes = Arrays.asList(vehicleMake1, vehicleMake2);
        when(vehicleMakeRepository.findAll()).thenReturn(vehicleMakes);

        List<VehicleMake> result = vehicleMakeService.findAll();

        assertEquals(2, result.size());
        assertEquals(vehicleMakes, result);
        verify(vehicleMakeRepository, times(1)).findAll();
    }

    @Test
    public void VehicleMakeService_findById_shouldReturnVehicleMakeById() {
        VehicleMake vehicleMake = new VehicleMake();
        when(vehicleMakeRepository.findById(1)).thenReturn(Optional.of(vehicleMake));

        Optional<VehicleMake> result = vehicleMakeService.findById(1);

        assertTrue(result.isPresent());
        assertEquals(vehicleMake, result.get());
        verify(vehicleMakeRepository, times(1)).findById(1);
    }

    @Test
    public void VehicleMakeService_save_shouldSaveVehicleMake() {
        VehicleMake vehicleMake = new VehicleMake();

        vehicleMakeService.save(vehicleMake);

        verify(vehicleMakeRepository, times(1)).save(vehicleMake);
    }

    @Test
    public void VehicleMakeService_delete_shouldDeleteVehicleMakeById() {
        vehicleMakeService.delete(1);

        verify(vehicleMakeRepository, times(1)).deleteById(1);
    }
}
