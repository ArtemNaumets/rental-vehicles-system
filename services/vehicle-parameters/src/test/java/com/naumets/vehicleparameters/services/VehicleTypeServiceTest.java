package com.naumets.vehicleparameters.services;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

import com.naumets.vehicleparameters.models.VehicleType;
import com.naumets.vehicleparameters.repositories.VehicleTypeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


public class VehicleTypeServiceTest {

    @Mock
    private VehicleTypeRepository vehicleTypeRepository;

    @InjectMocks
    private VehicleTypeService vehicleTypeService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void VehicleTypeService_findAll_shouldReturnAllVehicleTypes() {
        VehicleType vehicleType1 = new VehicleType();
        VehicleType vehicleType2 = new VehicleType();
        List<VehicleType> vehicleTypes = Arrays.asList(vehicleType1, vehicleType2);
        when(vehicleTypeRepository.findAll()).thenReturn(vehicleTypes);

        List<VehicleType> result = vehicleTypeService.findAll();

        assertEquals(2, result.size());
        assertEquals(vehicleTypes, result);
        verify(vehicleTypeRepository, times(1)).findAll();
    }

    @Test
    public void VehicleTypeService_findById_shouldReturnVehicleTypeById() {
        VehicleType vehicleType = new VehicleType();
        when(vehicleTypeRepository.findById(1)).thenReturn(Optional.of(vehicleType));

        Optional<VehicleType> result = vehicleTypeService.findById(1);

        assertTrue(result.isPresent());
        assertEquals(vehicleType, result.get());
        verify(vehicleTypeRepository, times(1)).findById(1);
    }

    @Test
    public void VehicleTypeService_save_shouldSaveVehicleType() {
        VehicleType vehicleType = new VehicleType();

        vehicleTypeService.save(vehicleType);

        verify(vehicleTypeRepository, times(1)).save(vehicleType);
    }

    @Test
    public void VehicleTypeService_delete_shouldDeleteVehicleTypeById() {
        vehicleTypeService.delete(1);

        verify(vehicleTypeRepository, times(1)).deleteById(1);
    }
}
