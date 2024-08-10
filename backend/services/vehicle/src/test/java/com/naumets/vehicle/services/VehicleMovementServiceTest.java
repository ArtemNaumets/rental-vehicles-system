package com.naumets.vehicle.services;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.naumets.vehicle.models.VehicleMovement;
import com.naumets.vehicle.repositories.VehicleMovementRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


public class VehicleMovementServiceTest {

    @Mock
    private VehicleMovementRepository vehicleMovementRepository;

    @InjectMocks
    private VehicleMovementService vehicleMovementService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void VehicleMovementService_findAll_shouldReturnAllVehicleMovements() {
        VehicleMovement vehicleMovement1 = new VehicleMovement();
        VehicleMovement vehicleMovement2 = new VehicleMovement();
        List<VehicleMovement> vehicleMovements = Arrays.asList(vehicleMovement1, vehicleMovement2);
        when(vehicleMovementRepository.findAll()).thenReturn(vehicleMovements);

        List<VehicleMovement> result = vehicleMovementService.findAll();

        assertEquals(2, result.size());
        assertEquals(vehicleMovements, result);
        verify(vehicleMovementRepository, times(1)).findAll();
    }

    @Test
    public void VehicleMovementService_findById_shouldReturnVehicleMovementById() {
        VehicleMovement vehicleMovement = new VehicleMovement();
        when(vehicleMovementRepository.findById(1)).thenReturn(Optional.of(vehicleMovement));

        Optional<VehicleMovement> result = vehicleMovementService.findById(1);

        assertTrue(result.isPresent());
        assertEquals(vehicleMovement, result.get());
        verify(vehicleMovementRepository, times(1)).findById(1);
    }

    @Test
    public void VehicleMovementService_save_shouldSaveVehicleMovement() {
        VehicleMovement vehicleMovement = new VehicleMovement();

        vehicleMovementService.save(vehicleMovement);

        verify(vehicleMovementRepository, times(1)).save(vehicleMovement);
    }

    @Test
    public void VehicleMovementService_delete_shouldDeleteVehicleMovementById() {
        vehicleMovementService.delete(1);

        verify(vehicleMovementRepository, times(1)).deleteById(1);
    }
}
