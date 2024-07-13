package com.naumets.vehicle.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.naumets.vehicle.models.Vehicle;
import com.naumets.vehicle.services.VehicleService;
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
@WebMvcTest(VehicleController.class)
@AutoConfigureMockMvc
public class VehicleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VehicleService vehicleService;

    @Autowired
    private ObjectMapper objectMapper;

    private Vehicle vehicle1;
    private Vehicle vehicle2;

    @BeforeEach
    void setUp() {
        vehicle1 = new Vehicle();
        vehicle1.setId(1);
        vehicle1.setName("Vehicle 1");
        vehicle1.setVehicletypeid(1);
        vehicle1.setVehicleNumber("VN1");
        vehicle1.setRegistrationDate(new Date());
        vehicle1.setAcquisitionDate(new Date());
        vehicle1.setDescription("Description 1");
        vehicle1.setVehiclemakeid(1);
        vehicle1.setPower("Power 1");
        vehicle1.setFuelCapacity("Fuel 1");
        vehicle1.setVehiclestatusid(1);
        vehicle1.setNetWeight("Weight 1");
        vehicle1.setEmployeeid(1);
        vehicle1.setVehiclemodelid(1);
        vehicle1.setLocationid(1);
        vehicle1.setRemarks("Remarks 1");

        vehicle2 = new Vehicle();
        vehicle2.setId(2);
        vehicle2.setName("Vehicle 2");
        vehicle2.setVehicletypeid(2);
        vehicle2.setVehicleNumber("VN2");
        vehicle2.setRegistrationDate(new Date());
        vehicle2.setAcquisitionDate(new Date());
        vehicle2.setDescription("Description 2");
        vehicle2.setVehiclemakeid(2);
        vehicle2.setPower("Power 2");
        vehicle2.setFuelCapacity("Fuel 2");
        vehicle2.setVehiclestatusid(2);
        vehicle2.setNetWeight("Weight 2");
        vehicle2.setEmployeeid(2);
        vehicle2.setVehiclemodelid(2);
        vehicle2.setLocationid(2);
        vehicle2.setRemarks("Remarks 2");
    }

    @Test
    public void testFindAllVehicles() throws Exception {
        Mockito.when(vehicleService.findAll()).thenReturn(Arrays.asList(vehicle1, vehicle2));

        mockMvc.perform(get("/api/vehicle/vehicles/")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("Vehicle 1"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].name").value("Vehicle 2"));
    }

    @Test
    public void testFindVehicleById() throws Exception {
        int vehicleId = 1;
        Mockito.when(vehicleService.findById(vehicleId)).thenReturn(Optional.of(vehicle1));

        mockMvc.perform(get("/api/vehicle/vehicles/findById/{id}", vehicleId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Vehicle 1"));
    }

    @Test
    public void testAddNewVehicle() throws Exception {
        Vehicle newVehicle = new Vehicle();
        newVehicle.setName("New Vehicle");
        newVehicle.setVehicletypeid(3);
        newVehicle.setVehicleNumber("VN3");
        newVehicle.setRegistrationDate(new Date());
        newVehicle.setAcquisitionDate(new Date());
        newVehicle.setDescription("Description 3");
        newVehicle.setVehiclemakeid(3);
        newVehicle.setPower("Power 3");
        newVehicle.setFuelCapacity("Fuel 3");
        newVehicle.setVehiclestatusid(3);
        newVehicle.setNetWeight("Weight 3");
        newVehicle.setEmployeeid(3);
        newVehicle.setVehiclemodelid(3);
        newVehicle.setLocationid(3);
        newVehicle.setRemarks("Remarks 3");

        Mockito.when(vehicleService.save(Mockito.any(Vehicle.class))).thenReturn(newVehicle);

        mockMvc.perform(post("/api/vehicle/vehicles/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newVehicle)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("New Vehicle"))
                .andExpect(jsonPath("$.remarks").value("Remarks 3"));
    }

    @Test
    public void testUpdateVehicle() throws Exception {
        Vehicle updatedVehicle = new Vehicle();
        updatedVehicle.setId(1);
        updatedVehicle.setName("Updated Vehicle");
        updatedVehicle.setVehicletypeid(1);
        updatedVehicle.setVehicleNumber("Updated VN1");
        updatedVehicle.setRegistrationDate(new Date());
        updatedVehicle.setAcquisitionDate(new Date());
        updatedVehicle.setDescription("Updated Description 1");
        updatedVehicle.setVehiclemakeid(1);
        updatedVehicle.setPower("Updated Power 1");
        updatedVehicle.setFuelCapacity("Updated Fuel 1");
        updatedVehicle.setVehiclestatusid(1);
        updatedVehicle.setNetWeight("Updated Weight 1");
        updatedVehicle.setEmployeeid(1);
        updatedVehicle.setVehiclemodelid(1);
        updatedVehicle.setLocationid(1);
        updatedVehicle.setRemarks("Updated Remarks 1");

        Mockito.when(vehicleService.save(Mockito.any(Vehicle.class))).thenReturn(updatedVehicle);

        mockMvc.perform(put("/api/vehicle/vehicles/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedVehicle)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Updated Vehicle"))
                .andExpect(jsonPath("$.remarks").value("Updated Remarks 1"));
    }

    @Test
    public void testDeleteVehicle() throws Exception {
        int vehicleId = 1;

        mockMvc.perform(delete("/api/vehicle/vehicles/delete/{id}", vehicleId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        Mockito.verify(vehicleService, Mockito.times(1)).delete(vehicleId);
    }
}
