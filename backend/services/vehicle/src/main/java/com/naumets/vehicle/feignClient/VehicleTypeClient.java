package com.naumets.vehicle.feignClient;

import com.naumets.vehicle.dtos.VehicleTypeDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "vehicle-parameters-service")
public interface VehicleTypeClient {

    @GetMapping("api/vehicleParameters/vehicleTypes/by-description")
    VehicleTypeDTO getVehicleTypeByDescription(@RequestParam("description") String description);
}
