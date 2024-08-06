package com.naumets.hrconfigurations.controllers;

import com.naumets.hrconfigurations.controllers.JobTitleController;
import com.naumets.hrconfigurations.models.JobTitle;
import com.naumets.hrconfigurations.services.JobTitleService;
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

public class JobTitleControllerTest {

    @InjectMocks
    private JobTitleController jobTitleController;

    @Mock
    private JobTitleService jobTitleService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindAll() {
        List<JobTitle> jobTitles = Arrays.asList(new JobTitle(), new JobTitle());
        when(jobTitleService.findAll()).thenReturn(jobTitles);

        List<JobTitle> response = jobTitleController.findAll();

        assertEquals(jobTitles, response);
    }

    @Test
    public void testFindById() {
        JobTitle jobTitle = new JobTitle();
        Optional<JobTitle> optionalJobTitle = Optional.of(jobTitle);
        when(jobTitleService.findById(1)).thenReturn(optionalJobTitle);

        Optional<JobTitle> response = jobTitleController.findById(1);

        assertEquals(optionalJobTitle, response);
    }

    @Test
    public void testAddNew() {
        JobTitle jobTitle = new JobTitle();
        when(jobTitleService.save(jobTitle)).thenReturn(jobTitle);

        ResponseEntity<JobTitle> response = jobTitleController.addNew(jobTitle);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(jobTitle, response.getBody());
    }

    @Test
    public void testUpdate() {
        JobTitle jobTitle = new JobTitle();
        when(jobTitleService.save(jobTitle)).thenReturn(jobTitle);

        JobTitle response = jobTitleController.update(jobTitle.getId(),jobTitle);

        assertEquals(jobTitle, response);
    }

    @Test
    public void testDelete() {
        doNothing().when(jobTitleService).delete(1);

        ResponseEntity<Void> response = jobTitleController.delete(1);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(jobTitleService, times(1)).delete(1);
    }
}
