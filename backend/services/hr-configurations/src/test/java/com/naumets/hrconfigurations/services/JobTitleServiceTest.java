package com.naumets.hrconfigurations.services;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.naumets.hrconfigurations.models.JobTitle;
import com.naumets.hrconfigurations.repositories.JobTitleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


public class JobTitleServiceTest {

    @Mock
    private JobTitleRepository jobTitleRepository;

    @InjectMocks
    private JobTitleService jobTitleService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void JobTitleService_findAll_shouldReturnAllJobTitles() {
        JobTitle jobTitle1 = new JobTitle();
        JobTitle jobTitle2 = new JobTitle();
        List<JobTitle> jobTitles = Arrays.asList(jobTitle1, jobTitle2);
        when(jobTitleRepository.findAll()).thenReturn(jobTitles);

        List<JobTitle> result = jobTitleService.findAll();

        assertEquals(2, result.size());
        assertEquals(jobTitles, result);
        verify(jobTitleRepository, times(1)).findAll();
    }

    @Test
    public void JobTitleService_findById_shouldReturnJobTitleById() {
        JobTitle jobTitle = new JobTitle();
        when(jobTitleRepository.findById(1)).thenReturn(Optional.of(jobTitle));

        Optional<JobTitle> result = jobTitleService.findById(1);

        assertTrue(result.isPresent());
        assertEquals(jobTitle, result.get());
        verify(jobTitleRepository, times(1)).findById(1);
    }

    @Test
    public void JobTitleService_delete_shouldDeleteJobTitleById() {
        jobTitleService.delete(1);

        verify(jobTitleRepository, times(1)).deleteById(1);
    }

    @Test
    public void JobTitleService_save_shouldSaveJobTitle() {
        JobTitle jobTitle = new JobTitle();

        jobTitleService.save(jobTitle);

        verify(jobTitleRepository, times(1)).save(jobTitle);
    }
}
