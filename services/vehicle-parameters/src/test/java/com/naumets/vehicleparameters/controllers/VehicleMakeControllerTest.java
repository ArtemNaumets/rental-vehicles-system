package com.naumets.vehicleparameters.controllers;

import com.naumets.vehicleparameters.controllers.VehicleMakeController;
import com.naumets.vehicleparameters.models.VehicleMake;
import com.naumets.vehicleparameters.services.VehicleMakeService;
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

public class VehicleMakeControllerTest {

    @InjectMocks
    private VehicleMakeController vehicleMakeController;

    @Mock
    private VehicleMakeService vehicleMakeService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindAll() {
        List<VehicleMake> vehicleMakes = Arrays.asList(new VehicleMake(), new VehicleMake());
        when(vehicleMakeService.findAll()).thenReturn(vehicleMakes);

        ResponseEntity<List<VehicleMake>> response = vehicleMakeController.findAll();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(vehicleMakes, response.getBody());
    }

    @Test
    public void testFindById() {
        VehicleMake vehicleMake = new VehicleMake();
        Optional<VehicleMake> optionalVehicleMake = Optional.of(vehicleMake);
        when(vehicleMakeService.findById(1)).thenReturn(optionalVehicleMake);

        ResponseEntity<Optional<VehicleMake>> response = vehicleMakeController.findById(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(optionalVehicleMake, response.getBody());
    }

    @Test
    public void testAddNew() {
        VehicleMake vehicleMake = new VehicleMake();
        when(vehicleMakeService.save(vehicleMake)).thenReturn(vehicleMake);

        ResponseEntity<VehicleMake> response = vehicleMakeController.addNew(vehicleMake);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(vehicleMake, response.getBody());
    }

    @Test
    public void testUpdate() {
        VehicleMake vehicleMake = new VehicleMake();
        when(vehicleMakeService.save(vehicleMake)).thenReturn(vehicleMake);

        ResponseEntity<VehicleMake> response = vehicleMakeController.update(vehicleMake, 1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(vehicleMake, response.getBody());
    }

    @Test
    public void testDelete() {
        doNothing().when(vehicleMakeService).delete(1);

        ResponseEntity<Void> response = vehicleMakeController.delete(1);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(vehicleMakeService, times(1)).delete(1);
    }
}
