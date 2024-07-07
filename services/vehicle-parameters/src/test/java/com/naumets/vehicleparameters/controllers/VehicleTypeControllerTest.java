package com.naumets.vehicleparameters.controllers;

import com.naumets.vehicleparameters.controllers.VehicleTypeController;
import com.naumets.vehicleparameters.models.VehicleType;
import com.naumets.vehicleparameters.services.VehicleTypeService;
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

public class VehicleTypeControllerTest {

    @InjectMocks
    private VehicleTypeController vehicleTypeController;

    @Mock
    private VehicleTypeService vehicleTypeService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindAll() {
        List<VehicleType> vehicleTypes = Arrays.asList(new VehicleType(), new VehicleType());
        when(vehicleTypeService.findAll()).thenReturn(vehicleTypes);

        List<VehicleType> response = vehicleTypeController.findAll();

        assertEquals(vehicleTypes, response);
    }

    @Test
    public void testFindById() {
        VehicleType vehicleType = new VehicleType();
        Optional<VehicleType> optionalVehicleType = Optional.of(vehicleType);
        when(vehicleTypeService.findById(1)).thenReturn(optionalVehicleType);

        Optional<VehicleType> response = vehicleTypeController.findById(1);

        assertEquals(optionalVehicleType, response);
    }

    @Test
    public void testAddNew() {
        VehicleType vehicleType = new VehicleType();
        when(vehicleTypeService.save(vehicleType)).thenReturn(vehicleType);

        ResponseEntity<VehicleType> response = vehicleTypeController.addNew(vehicleType);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(vehicleType, response.getBody());
    }

    @Test
    public void testUpdate() {
        VehicleType vehicleType = new VehicleType();
        when(vehicleTypeService.save(vehicleType)).thenReturn(vehicleType);

        VehicleType response = vehicleTypeController.update(vehicleType);

        assertEquals(vehicleType, response);
    }

    @Test
    public void testDelete() {
        doNothing().when(vehicleTypeService).delete(1);

        ResponseEntity<Void> response = vehicleTypeController.delete(1);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(vehicleTypeService, times(1)).delete(1);
    }
}
