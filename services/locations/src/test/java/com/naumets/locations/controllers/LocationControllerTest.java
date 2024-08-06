package com.naumets.locations.controllers;

import com.naumets.locations.controllers.LocationController;
import com.naumets.locations.models.Location;
import com.naumets.locations.services.LocationService;
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

public class LocationControllerTest {

    @InjectMocks
    private LocationController locationController;

    @Mock
    private LocationService locationService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindAll() {
        List<Location> locations = Arrays.asList(new Location(), new Location());
        when(locationService.findAll()).thenReturn(locations);

        List<Location> response = locationController.findAll();

        assertEquals(locations, response);
    }

    @Test
    public void testFindById() {
        Location location = new Location();
        Optional<Location> optionalLocation = Optional.of(location);
        when(locationService.findById(1)).thenReturn(optionalLocation);

        Optional<Location> response = locationController.findById(1);

        assertEquals(optionalLocation, response);
    }

    @Test
    public void testAddNew() {
        Location location = new Location();
        when(locationService.save(location)).thenReturn(location);

        ResponseEntity<Location> response = locationController.addNew(location);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(location, response.getBody());
    }

    @Test
    public void testUpdate() {
        Location location = new Location();
        when(locationService.save(location)).thenReturn(location);

        Location response = locationController.update(location.getId(), location);

        assertEquals(location, response);
    }

    @Test
    public void testDelete() {
        doNothing().when(locationService).delete(1);

        ResponseEntity<Void> response = locationController.delete(1);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(locationService, times(1)).delete(1);
    }
}
