package com.naumets.vehicleparameters.services;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

import com.naumets.vehicleparameters.models.VehicleModel;
import com.naumets.vehicleparameters.repositories.VehicleModelRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


public class VehicleModelServiceTest {

    @Mock
    private VehicleModelRepository vehicleModelRepository;

    @InjectMocks
    private VehicleModelService vehicleModelService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void VehicleModelService_findAll_shouldReturnAllVehicleModels() {
        VehicleModel vehicleModel1 = new VehicleModel();
        VehicleModel vehicleModel2 = new VehicleModel();
        List<VehicleModel> vehicleModels = Arrays.asList(vehicleModel1, vehicleModel2);
        when(vehicleModelRepository.findAll()).thenReturn(vehicleModels);

        List<VehicleModel> result = vehicleModelService.findAll();

        assertEquals(2, result.size());
        assertEquals(vehicleModels, result);
        verify(vehicleModelRepository, times(1)).findAll();
    }

    @Test
    public void VehicleModelService_findById_shouldReturnVehicleModelById() {
        VehicleModel vehicleModel = new VehicleModel();
        when(vehicleModelRepository.findById(1)).thenReturn(Optional.of(vehicleModel));

        Optional<VehicleModel> result = vehicleModelService.findById(1);

        assertTrue(result.isPresent());
        assertEquals(vehicleModel, result.get());
        verify(vehicleModelRepository, times(1)).findById(1);
    }

    @Test
    public void VehicleModelService_save_shouldSaveVehicleModel() {
        VehicleModel vehicleModel = new VehicleModel();

        vehicleModelService.save(vehicleModel);

        verify(vehicleModelRepository, times(1)).save(vehicleModel);
    }

    @Test
    public void VehicleModelService_delete_shouldDeleteVehicleModelById() {
        vehicleModelService.delete(1);

        verify(vehicleModelRepository, times(1)).deleteById(1);
    }
}
