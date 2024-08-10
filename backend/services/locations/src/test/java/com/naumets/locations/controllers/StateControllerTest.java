package com.naumets.locations.controllers;

import com.naumets.locations.controllers.StateController;
import com.naumets.locations.models.State;
import com.naumets.locations.services.StateService;
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

public class StateControllerTest {

    @InjectMocks
    private StateController stateController;

    @Mock
    private StateService stateService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindAll() {
        List<State> states = Arrays.asList(new State(), new State());
        when(stateService.findAll()).thenReturn(states);

        List<State> response = stateController.findAll();

        assertEquals(states, response);
    }

    @Test
    public void testFindById() {
        State state = new State();
        Optional<State> optionalState = Optional.of(state);
        when(stateService.findById(1)).thenReturn(optionalState);

        Optional<State> response = stateController.findById(1);

        assertEquals(optionalState, response);
    }

    @Test
    public void testAddNew() {
        State state = new State();
        when(stateService.save(state)).thenReturn(state);

        ResponseEntity<State> response = stateController.addNew(state);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(state, response.getBody());
    }

    @Test
    public void testUpdate() {
        State state = new State();
        when(stateService.save(state)).thenReturn(state);

        State response = stateController.update(state.getId(), state);

        assertEquals(state, response);
    }

    @Test
    public void testDelete() {
        doNothing().when(stateService).delete(1);

        ResponseEntity<Void> response = stateController.delete(1);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(stateService, times(1)).delete(1);
    }
}
