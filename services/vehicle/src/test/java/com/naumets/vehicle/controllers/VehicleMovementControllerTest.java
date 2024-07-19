package com.naumets.vehicle.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.naumets.vehicle.controllers.VehicleMovementController;
import com.naumets.vehicle.models.VehicleMovement;
import com.naumets.vehicle.models.Vehicle;
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



        vehicleMovement1 = new VehicleMovement(1, vehicle1, 1, 1, new Date(), 2, new Date(), "Remarks 1");

        Vehicle vehicle2 = new Vehicle();
        vehicle2.setId(2);
        vehicle2.setName("Vehicle 2");


        vehicleMovement2 = new VehicleMovement(2, vehicle2, 2, 3, new Date(), 4, new Date(), "Remarks 2");
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
                .andExpect(jsonPath("$[0].locationid1").value(1))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].vehicle.id").value(2))
                .andExpect(jsonPath("$[1].vehicle.name").value("Vehicle 2"))
                .andExpect(jsonPath("$[1].locationid1").value(3));
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
                .andExpect(jsonPath("$.locationid1").value(1));
    }

    @Test
    public void testAddNewVehicleMovement() throws Exception {
        Vehicle vehicle1 = new Vehicle();
        vehicle1.setId(3);
        vehicle1.setName("Vehicle 3");






        VehicleMovement newVehicleMovement = new VehicleMovement(1, vehicle1, 3, 5, new Date(), 6, new Date(), "New Remarks");

        Mockito.when(vehicleMovementService.save(Mockito.any(VehicleMovement.class))).thenReturn(newVehicleMovement);

        mockMvc.perform(post("/api/vehicle/vehicleMovements/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newVehicleMovement)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.vehicle.name").value("Vehicle 3"))
                .andExpect(jsonPath("$.locationid1").value("5"))
                .andExpect(jsonPath("$.locationid2").value("6"))
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
