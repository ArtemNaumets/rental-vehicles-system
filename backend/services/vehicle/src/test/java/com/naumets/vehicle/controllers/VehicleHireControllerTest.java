package com.naumets.vehicle.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.naumets.vehicle.models.Vehicle;
import com.naumets.vehicle.models.VehicleHire;
import com.naumets.vehicle.services.VehicleHireService;
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
@WebMvcTest(VehicleHireController.class)
@AutoConfigureMockMvc
public class VehicleHireControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VehicleHireService vehicleHireService;

    @Autowired
    private ObjectMapper objectMapper;

    private VehicleHire vehicleHire1;
    private VehicleHire vehicleHire2;

    @BeforeEach
    void setUp() {
        vehicleHire1 = new VehicleHire();
        vehicleHire1.setId(1);
        vehicleHire1.setVehicle(new Vehicle());
        vehicleHire1.setVehicleid(1);
        vehicleHire1.setDateIn(new Date());
        vehicleHire1.setDateOut(new Date());
        vehicleHire1.setRemarks("Remarks 1");

        vehicleHire2 = new VehicleHire();
        vehicleHire2.setId(2);
        vehicleHire2.setVehicle(new Vehicle());
        vehicleHire2.setVehicleid(2);
        vehicleHire2.setDateIn(new Date());
        vehicleHire2.setDateOut(new Date());
        vehicleHire2.setRemarks("Remarks 2");
    }

    @Test
    public void testFindAllVehicleHires() throws Exception {
        Mockito.when(vehicleHireService.findAll()).thenReturn(Arrays.asList(vehicleHire1, vehicleHire2));

        mockMvc.perform(get("/api/vehicle/vehicleHires/")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].remarks").value("Remarks 1"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].remarks").value("Remarks 2"));
    }

    @Test
    public void testFindVehicleHireById() throws Exception {
        int vehicleHireId = 1;
        Mockito.when(vehicleHireService.findById(vehicleHireId)).thenReturn(Optional.of(vehicleHire1));

        mockMvc.perform(get("/api/vehicle/vehicleHires/findById/{id}", vehicleHireId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.remarks").value("Remarks 1"));
    }

    @Test
    public void testAddNewVehicleHire() throws Exception {
        VehicleHire newVehicleHire = new VehicleHire();
        newVehicleHire.setVehicle(new Vehicle());
        newVehicleHire.setVehicleid(3);
        newVehicleHire.setDateIn(new Date());
        newVehicleHire.setDateOut(new Date());
        newVehicleHire.setRemarks("New Remarks");

        Mockito.when(vehicleHireService.save(Mockito.any(VehicleHire.class))).thenReturn(newVehicleHire);

        mockMvc.perform(post("/api/vehicle/vehicleHires/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newVehicleHire)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.remarks").value("New Remarks"));
    }

    @Test
    public void testUpdateVehicleHire() throws Exception {
        VehicleHire updatedVehicleHire = new VehicleHire();
        updatedVehicleHire.setId(1);
        updatedVehicleHire.setVehicle(new Vehicle());
        updatedVehicleHire.setVehicleid(1);
        updatedVehicleHire.setDateIn(new Date());
        updatedVehicleHire.setDateOut(new Date());
        updatedVehicleHire.setRemarks("Updated Remarks");

        Mockito.when(vehicleHireService.save(Mockito.any(VehicleHire.class))).thenReturn(updatedVehicleHire);

        mockMvc.perform(put("/api/vehicle/vehicleHires/update/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedVehicleHire)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.remarks").value("Updated Remarks"));
    }

    @Test
    public void testDeleteVehicleHire() throws Exception {
        int vehicleHireId = 1;

        mockMvc.perform(delete("/api/vehicle/vehicleHires/delete/{id}", vehicleHireId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        Mockito.verify(vehicleHireService, Mockito.times(1)).delete(vehicleHireId);
    }
}
