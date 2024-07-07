package com.naumets.locations.services;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.naumets.locations.models.Location;
import com.naumets.locations.repositories.LocationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


public class LocationServiceTest {

    @Mock
    private LocationRepository locationRepository;

    @InjectMocks
    private LocationService locationService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void LocationService_findAll_shouldReturnAllLocations() {
        Location location1 = new Location();
        Location location2 = new Location();
        List<Location> locations = Arrays.asList(location1, location2);
        when(locationRepository.findAll()).thenReturn(locations);

        List<Location> result = locationService.findAll();

        assertEquals(2, result.size());
        assertEquals(locations, result);
        verify(locationRepository, times(1)).findAll();
    }

    @Test
    public void LocationService_findById_shouldReturnLocationById() {
        Location location = new Location();
        when(locationRepository.findById(1)).thenReturn(Optional.of(location));

        Optional<Location> result = locationService.findById(1);

        assertTrue(result.isPresent());
        assertEquals(location, result.get());
        verify(locationRepository, times(1)).findById(1);
    }

    @Test
    public void LocationService_save_shouldSaveLocation() {
        Location location = new Location();

        locationService.save(location);

        verify(locationRepository, times(1)).save(location);
    }

    @Test
    public void LocationService_delete_shouldDeleteLocationById() {
        locationService.delete(1);

        verify(locationRepository, times(1)).deleteById(1);
    }


    @Test
    public void LocationService_countAll_shouldReturnTotalNumberOfLocations() {
        when(locationRepository.countAllBy()).thenReturn(5);

        Integer result = locationService.countAll();

        assertEquals(5, result);
        verify(locationRepository, times(1)).countAllBy();
    }
}
