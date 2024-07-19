package com.naumets.vehicleparameters.mappers;

import com.naumets.vehicleparameters.dtos.VehicleTypeDTO;
import com.naumets.vehicleparameters.models.VehicleType;

public class VehicleTypeMapper {

    public static VehicleTypeDTO toDTO(VehicleType vehicleType) {
        return new VehicleTypeDTO(
                vehicleType.getId(),
                vehicleType.getDetails(),
                vehicleType.getDescription()
        );
    }

    public static VehicleType toEntity(VehicleTypeDTO vehicleTypeDTO) {
        VehicleType vehicleType = new VehicleType();
        vehicleType.setId(vehicleTypeDTO.getId());
        vehicleType.setDescription(vehicleTypeDTO.getDescription());
        vehicleType.setDetails(vehicleTypeDTO.getDetails());

        return vehicleType;

    }
}
