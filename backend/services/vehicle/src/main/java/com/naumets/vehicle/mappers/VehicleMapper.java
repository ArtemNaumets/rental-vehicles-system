package com.naumets.vehicle.mappers;

import com.naumets.vehicle.dtos.VehicleDTO;
import com.naumets.vehicle.models.Vehicle;

public class VehicleMapper {

    public static VehicleDTO toDTO(Vehicle vehicle) {
        return new VehicleDTO(
                vehicle.getId(),
                vehicle.getName(),
                vehicle.getVehicletypeid(),
                vehicle.getVehicleNumber(),
                vehicle.getRegistrationDate(),
                vehicle.getAcquisitionDate(),
                vehicle.getDescription(),
                vehicle.getVehiclemakeid(),
                vehicle.getPower(),
                vehicle.getFuelCapacity(),
                vehicle.getVehiclestatusid(),
                vehicle.getNetWeight(),
                vehicle.getEmployeeid(),
                vehicle.getVehiclemodelid(),
                vehicle.getLocationid(),
                vehicle.getRemarks()
        );
    }

    public static Vehicle toEntity(VehicleDTO vehicleDTO) {
        return new Vehicle(
                vehicleDTO.getId(),
                vehicleDTO.getName(),
                vehicleDTO.getVehicletypeid(),
                vehicleDTO.getVehicleNumber(),
                vehicleDTO.getRegistrationDate(),
                vehicleDTO.getAcquisitionDate(),
                vehicleDTO.getDescription(),
                vehicleDTO.getVehiclemakeid(),
                vehicleDTO.getPower(),
                vehicleDTO.getFuelCapacity(),
                vehicleDTO.getVehiclestatusid(),
                vehicleDTO.getNetWeight(),
                vehicleDTO.getEmployeeid(),
                vehicleDTO.getVehiclemodelid(),
                vehicleDTO.getLocationid(),
                vehicleDTO.getRemarks()
        );
    }
}
