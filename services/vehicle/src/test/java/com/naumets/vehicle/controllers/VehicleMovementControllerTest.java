package com.naumets.vehicle.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.naumets.vehicle.controllers.VehicleMovementController;
import com.naumets.vehicle.models.VehicleMovement;
import com.naumets.vehicle.models.Vehicle;
import com.naumets.vehicle.models.locations.Location;
import com.naumets.vehicle.services.VehicleMovementService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Date;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(VehicleMovementController.class)
@AutoConfigureMockMvc
public class VehicleMovementControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VehicleMovementService vehicleMovementService;

    @Autowired
    private ObjectMapper objectMapper;

    private VehicleMovement vehicleMovement1;
    private VehicleMovement vehicleMovement2;

    @BeforeEach
    void setUp() {
        Vehicle vehicle1 = new Vehicle();
        vehicle1.setId(1);
        vehicle1.setName("Vehicle 1");

        Location locationA = new Location();
        locationA.setId(1);
        locationA.setDescription("Location A");
        Location locationB = new Location();
        locationB.setId(2);
        locationB.setDescription("Location B");


        vehicleMovement1 = new VehicleMovement(1, vehicle1, 1, locationA, 1, new Date(), locationB, 2, new Date(), "Remarks 1");

        Vehicle vehicle2 = new Vehicle();
        vehicle2.setId(2);
        vehicle2.setName("Vehicle 2");

        Location locationC = new Location();
        locationC.setId(3);
        locationC.setDescription("Location C");
        Location locationD = new Location();
        locationD.setId(4);
        locationD.setDescription("Location D");

        vehicleMovement2 = new VehicleMovement(2, vehicle2, 2, locationC, 3, new Date(), locationD, 4, new Date(), "Remarks 2");
    }

    @Test
    public void testFindAllVehicleMovements() throws Exception {
        Mockito.when(vehicleMovementService.findAll()).thenReturn(Arrays.asList(vehicleMovement1, vehicleMovement2));

        mockMvc.perform(get("/api/vehicle/vehicleMovements/")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].vehicle.id").value(1))
                .andExpect(jsonPath("$[0].vehicle.name").value("Vehicle 1"))
                .andExpect(jsonPath("$[0].location1.id").value(1))
                .andExpect(jsonPath("$[0].location1.description").value("Location A"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].vehicle.id").value(2))
                .andExpect(jsonPath("$[1].vehicle.name").value("Vehicle 2"))
                .andExpect(jsonPath("$[1].location1.id").value(3))
                .andExpect(jsonPath("$[1].location1.description").value("Location C"));
    }

    @Test
    public void testFindVehicleMovementById() throws Exception {
        int vehicleMovementId = 1;
        Mockito.when(vehicleMovementService.findById(vehicleMovementId)).thenReturn(Optional.of(vehicleMovement1));

        mockMvc.perform(get("/api/vehicle/vehicleMovements/findById/{id}", vehicleMovementId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.vehicle.id").value(1))
                .andExpect(jsonPath("$.vehicle.name").value("Vehicle 1"))
                .andExpect(jsonPath("$.location1.id").value(1))
                .andExpect(jsonPath("$.location1.description").value("Location A"));
    }

    @Test
    public void testAddNewVehicleMovement() throws Exception {
        Vehicle vehicle1 = new Vehicle();
        vehicle1.setId(3);
        vehicle1.setName("Vehicle 3");

        Location location1 = new Location();
        location1.setId(5);
        location1.setDescription("Location E");

        Location location2 = new Location();
        location2.setId(6);
        location2.setDescription("Location F");




        VehicleMovement newVehicleMovement = new VehicleMovement(1, vehicle1, 3, location1, 5, new Date(), location2, 6, new Date(), "New Remarks");

        Mockito.when(vehicleMovementService.save(Mockito.any(VehicleMovement.class))).thenReturn(newVehicleMovement);

        mockMvc.perform(post("/api/vehicle/vehicleMovements/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newVehicleMovement)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.vehicle.name").value("Vehicle 3"))
                .andExpect(jsonPath("$.location1.description").value("Location E"))
                .andExpect(jsonPath("$.location2.description").value("Location F"))
                .andExpect(jsonPath("$.remarks").value("New Remarks"));
    }


    @Test
    public void testDeleteVehicleMovement() throws Exception {
        int vehicleMovementId = 1;

        mockMvc.perform(delete("/api/vehicle/vehicleMovements/delete/{id}", vehicleMovementId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}
