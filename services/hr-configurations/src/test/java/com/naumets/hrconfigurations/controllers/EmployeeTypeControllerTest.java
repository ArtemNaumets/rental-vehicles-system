package com.naumets.hrconfigurations.controllers;

import com.naumets.hrconfigurations.controllers.EmployeeTypeController;
import com.naumets.hrconfigurations.models.EmployeeType;
import com.naumets.hrconfigurations.services.EmployeeTypeService;
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

public class EmployeeTypeControllerTest {

    @InjectMocks
    private EmployeeTypeController employeeTypeController;

    @Mock
    private EmployeeTypeService employeeTypeService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindAll() {
        List<EmployeeType> employeeTypes = Arrays.asList(new EmployeeType(), new EmployeeType());
        when(employeeTypeService.findAll()).thenReturn(employeeTypes);

        List<EmployeeType> response = employeeTypeController.findAll();

        assertEquals(employeeTypes, response);
    }

    @Test
    public void testFindById() {
        EmployeeType employeeType = new EmployeeType();
        Optional<EmployeeType> optionalEmployeeType = Optional.of(employeeType);
        when(employeeTypeService.findById(1)).thenReturn(optionalEmployeeType);

        Optional<EmployeeType> response = employeeTypeController.findById(1);

        assertEquals(optionalEmployeeType, response);
    }

    @Test
    public void testAddNew() {
        EmployeeType employeeType = new EmployeeType();
        when(employeeTypeService.save(employeeType)).thenReturn(employeeType);

        ResponseEntity<EmployeeType> response = employeeTypeController.addNew(employeeType);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(employeeType, response.getBody());
    }

    @Test
    public void testUpdate() {
        EmployeeType employeeType = new EmployeeType();
        when(employeeTypeService.save(employeeType)).thenReturn(employeeType);

        EmployeeType response = employeeTypeController.update(employeeType.getId(), employeeType);

        assertEquals(employeeType, response);
    }

    @Test
    public void testDelete() {
        doNothing().when(employeeTypeService).delete(1);

        ResponseEntity<Void> response = employeeTypeController.delete(1);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(employeeTypeService, times(1)).delete(1);
    }
}
