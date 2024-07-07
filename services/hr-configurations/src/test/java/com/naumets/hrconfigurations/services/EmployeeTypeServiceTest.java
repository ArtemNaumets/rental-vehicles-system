package com.naumets.hrconfigurations.services;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.naumets.hrconfigurations.models.EmployeeType;
import com.naumets.hrconfigurations.repositories.EmployeeTypeRepository;
import com.naumets.hrconfigurations.services.EmployeeTypeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


public class EmployeeTypeServiceTest {

    @Mock
    private EmployeeTypeRepository employeeTypeRepository;

    @InjectMocks
    private EmployeeTypeService employeeTypeService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void EmployeeTypeService_findAll_shouldReturnAllEmployeeTypes() {
        EmployeeType type1 = new EmployeeType();
        EmployeeType type2 = new EmployeeType();
        List<EmployeeType> types = Arrays.asList(type1, type2);
        when(employeeTypeRepository.findAll()).thenReturn(types);

        List<EmployeeType> result = employeeTypeService.findAll();

        assertEquals(2, result.size());
        assertEquals(types, result);
        verify(employeeTypeRepository, times(1)).findAll();
    }

    @Test
    public void EmployeeTypeService_findById_shouldReturnEmployeeTypeById() {
        EmployeeType type = new EmployeeType();
        when(employeeTypeRepository.findById(1)).thenReturn(Optional.of(type));

        Optional<EmployeeType> result = employeeTypeService.findById(1);

        assertTrue(result.isPresent());
        assertEquals(type, result.get());
        verify(employeeTypeRepository, times(1)).findById(1);
    }

    @Test
    public void EmployeeTypeService_delete_shouldDeleteEmployeeTypeById() {
        employeeTypeService.delete(1);

        verify(employeeTypeRepository, times(1)).deleteById(1);
    }

    @Test
    public void EmployeeTypeService_save_shouldSaveEmployeeType() {
        EmployeeType type = new EmployeeType();

        employeeTypeService.save(type);

        verify(employeeTypeRepository, times(1)).save(type);
    }
}
