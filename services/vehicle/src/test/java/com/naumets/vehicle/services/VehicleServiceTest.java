package com.naumets.vehicle.services;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

import com.naumets.vehicle.models.Vehicle;
import com.naumets.vehicle.repositories.VehicleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


public class VehicleServiceTest {

    @Mock
    private VehicleRepository vehicleRepository;

    @InjectMocks
    private VehicleService vehicleService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void VehicleService_findAll_shouldReturnAllVehicles() {
        Vehicle vehicle1 = new Vehicle();
        Vehicle vehicle2 = new Vehicle();
        List<Vehicle> vehicles = Arrays.asList(vehicle1, vehicle2);
        when(vehicleRepository.findAll()).thenReturn(vehicles);

        List<Vehicle> result = vehicleService.findAll();

        assertEquals(2, result.size());
        assertEquals(vehicles, result);
        verify(vehicleRepository, times(1)).findAll();
    }

    @Test
    public void VehicleService_findById_shouldReturnVehicleById() {
        Vehicle vehicle = new Vehicle();
        when(vehicleRepository.findById(1)).thenReturn(Optional.of(vehicle));

        Optional<Vehicle> result = vehicleService.findById(1);

        assertTrue(result.isPresent());
        assertEquals(vehicle, result.get());
        verify(vehicleRepository, times(1)).findById(1);
    }

    @Test
    public void VehicleService_save_shouldSaveVehicle() {
        Vehicle vehicle = new Vehicle();

        vehicleService.save(vehicle);

        verify(vehicleRepository, times(1)).save(vehicle);
    }

    @Test
    public void VehicleService_delete_shouldDeleteVehicleById() {
        vehicleService.delete(1);

        verify(vehicleRepository, times(1)).deleteById(1);
    }

    @Test
    public void VehicleService_countVehiclesByTypes_shouldReturnVehicleCountByType() {
        List<String> types = Arrays.asList("SUV", "Sedan");
        when(vehicleRepository.countVehiclesByType("SUV")).thenReturn(5);
        when(vehicleRepository.countVehiclesByType("Sedan")).thenReturn(3);

        Map<String, Integer> result = vehicleService.countVehiclesByTypes(types);

        assertEquals(2, result.size());
        assertEquals(5, result.get("SUV"));
        assertEquals(3, result.get("Sedan"));
        verify(vehicleRepository, times(1)).countVehiclesByType("SUV");
        verify(vehicleRepository, times(1)).countVehiclesByType("Sedan");
    }
}
