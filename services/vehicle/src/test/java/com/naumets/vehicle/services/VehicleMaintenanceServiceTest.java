package com.naumets.vehicle.services;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.naumets.vehicle.models.VehicleMaintenance;
import com.naumets.vehicle.repositories.VehicleMaintenanceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


public class VehicleMaintenanceServiceTest {

    @Mock
    private VehicleMaintenanceRepository vehicleMaintenanceRepository;

    @InjectMocks
    private VehicleMaintenanceService vehicleMaintenanceService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void VehicleMaintenanceService_findAll_shouldReturnAllVehicleMaintenances() {
        VehicleMaintenance vehicleMaintenance1 = new VehicleMaintenance();
        VehicleMaintenance vehicleMaintenance2 = new VehicleMaintenance();
        List<VehicleMaintenance> vehicleMaintenances = Arrays.asList(vehicleMaintenance1, vehicleMaintenance2);
        when(vehicleMaintenanceRepository.findAll()).thenReturn(vehicleMaintenances);

        List<VehicleMaintenance> result = vehicleMaintenanceService.findAll();

        assertEquals(2, result.size());
        assertEquals(vehicleMaintenances, result);
        verify(vehicleMaintenanceRepository, times(1)).findAll();
    }

    @Test
    public void VehicleMaintenanceService_findById_shouldReturnVehicleMaintenanceById() {
        VehicleMaintenance vehicleMaintenance = new VehicleMaintenance();
        when(vehicleMaintenanceRepository.findById(1)).thenReturn(Optional.of(vehicleMaintenance));

        Optional<VehicleMaintenance> result = vehicleMaintenanceService.findById(1);

        assertTrue(result.isPresent());
        assertEquals(vehicleMaintenance, result.get());
        verify(vehicleMaintenanceRepository, times(1)).findById(1);
    }

    @Test
    public void VehicleMaintenanceService_save_shouldSaveVehicleMaintenance() {
        VehicleMaintenance vehicleMaintenance = new VehicleMaintenance();

        vehicleMaintenanceService.save(vehicleMaintenance);

        verify(vehicleMaintenanceRepository, times(1)).save(vehicleMaintenance);
    }

    @Test
    public void VehicleMaintenanceService_delete_shouldDeleteVehicleMaintenanceById() {
        vehicleMaintenanceService.delete(1);

        verify(vehicleMaintenanceRepository, times(1)).deleteById(1);
    }

    @Test
    public void VehicleMaintenanceService_findLast10_shouldReturnLast10VehicleMaintenances() {
        VehicleMaintenance vehicleMaintenance1 = new VehicleMaintenance();
        VehicleMaintenance vehicleMaintenance2 = new VehicleMaintenance();
        List<VehicleMaintenance> vehicleMaintenances = Arrays.asList(vehicleMaintenance1, vehicleMaintenance2);
        when(vehicleMaintenanceRepository.findLast10()).thenReturn(vehicleMaintenances);

        List<VehicleMaintenance> result = vehicleMaintenanceService.findLast10();

        assertEquals(vehicleMaintenances.size(), result.size());
        assertEquals(vehicleMaintenances, result);
        verify(vehicleMaintenanceRepository, times(1)).findLast10();
    }
}
