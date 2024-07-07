package com.naumets.locations.services;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.naumets.locations.models.State;
import com.naumets.locations.repositories.StateRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


public class StateServiceTest {

    @Mock
    private StateRepository stateRepository;

    @InjectMocks
    private StateService stateService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void StateService_findAll_shouldReturnAllStates() {
        State state1 = new State();
        State state2 = new State();
        List<State> states = Arrays.asList(state1, state2);
        when(stateRepository.findAll()).thenReturn(states);

        List<State> result = stateService.findAll();

        assertEquals(2, result.size());
        assertEquals(states, result);
        verify(stateRepository, times(1)).findAll();
    }

    @Test
    public void StateService_findById_shouldReturnStateById() {
        State state = new State();
        when(stateRepository.findById(1)).thenReturn(Optional.of(state));

        Optional<State> result = stateService.findById(1);

        assertTrue(result.isPresent());
        assertEquals(state, result.get());
        verify(stateRepository, times(1)).findById(1);
    }

    @Test
    public void StateService_save_shouldSaveState() {
        State state = new State();

        stateService.save(state);

        verify(stateRepository, times(1)).save(state);
    }

    @Test
    public void StateService_delete_shouldDeleteStateById() {
        stateService.delete(1);

        verify(stateRepository, times(1)).deleteById(1);
    }
}
