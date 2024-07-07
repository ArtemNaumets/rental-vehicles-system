package com.naumets.locations.services;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.naumets.locations.models.Country;
import com.naumets.locations.repositories.CountryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


public class CountryServiceTest {

    @Mock
    private CountryRepository countryRepository;

    @InjectMocks
    private CountryService countryService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void CountryService_findAll_shouldReturnAllCountries() {
        Country country1 = new Country();
        Country country2 = new Country();
        List<Country> countries = Arrays.asList(country1, country2);
        when(countryRepository.findAll()).thenReturn(countries);

        List<Country> result = countryService.findAll();

        assertEquals(2, result.size());
        assertEquals(countries, result);
        verify(countryRepository, times(1)).findAll();
    }

    @Test
    public void CountryService_findById_shouldReturnCountryById() {
        Country country = new Country();
        when(countryRepository.findById(1)).thenReturn(Optional.of(country));

        Optional<Country> result = countryService.findById(1);

        assertTrue(result.isPresent());
        assertEquals(country, result.get());
        verify(countryRepository, times(1)).findById(1);
    }

    @Test
    public void CountryService_delete_shouldDeleteCountryById() {
        countryService.delete(1);

        verify(countryRepository, times(1)).deleteById(1);
    }

    @Test
    public void CountryService_save_shouldSaveCountry() {
        Country country = new Country();

        countryService.save(country);

        verify(countryRepository, times(1)).save(country);
    }
}
