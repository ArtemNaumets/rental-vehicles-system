package com.naumets.vehicleparameters.controllers;

import com.naumets.vehicleparameters.controllers.VehicleModelController;
import com.naumets.vehicleparameters.models.VehicleModel;
import com.naumets.vehicleparameters.services.VehicleModelService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class VehicleModelControllerTest {

    @InjectMocks
    private VehicleModelController vehicleModelController;

    @Mock
    private VehicleModelService vehicleModelService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindAll() {
        List<VehicleModel> vehicleModels = Arrays.asList(new VehicleModel(), new VehicleModel());
        when(vehicleModelService.findAll()).thenReturn(vehicleModels);

        List<VehicleModel> response = vehicleModelController.findAll();

        assertEquals(vehicleModels, response);
    }

    @Test
    public void testFindById() {
        VehicleModel vehicleModel = new VehicleModel();
        Optional<VehicleModel> optionalVehicleModel = Optional.of(vehicleModel);
        when(vehicleModelService.findById(1)).thenReturn(optionalVehicleModel);

        Optional<VehicleModel> response = vehicleModelController.findById(1);

        assertEquals(optionalVehicleModel, response);
    }

    @Test
    public void testAddNew() {
        VehicleModel vehicleModel = new VehicleModel();
        when(vehicleModelService.save(vehicleModel)).thenReturn(vehicleModel);

        ResponseEntity<VehicleModel> response = vehicleModelController.addNew(vehicleModel);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(vehicleModel, response.getBody());
    }

    @Test
    public void testUpdate() {
        VehicleModel vehicleModel = new VehicleModel();
        when(vehicleModelService.save(vehicleModel)).thenReturn(vehicleModel);

        VehicleModel response = vehicleModelController.update(vehicleModel);

        assertEquals(vehicleModel, response);
    }

    @Test
    public void testDelete() {
        doNothing().when(vehicleModelService).delete(1);

        ResponseEntity<Void> response = vehicleModelController.delete(1);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(vehicleModelService, times(1)).delete(1);
    }
}
