package com.naumets.vehicle.kafka;

import com.naumets.vehicle.dtos.ClientDTO;
import com.naumets.vehicle.dtos.VehicleDTO;

import java.util.Date;

public record HireConfirmation(
        ClientDTO client,
        Date dateIn,
        Date dateOut,
        String price,
        VehicleDTO vehicle
) {

}
