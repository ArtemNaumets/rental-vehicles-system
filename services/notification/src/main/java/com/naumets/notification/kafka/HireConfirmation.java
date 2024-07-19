package com.naumets.notification.kafka;


import com.naumets.notification.dtos.ClientDTO;
import com.naumets.notification.dtos.VehicleDTO;

import java.util.Date;

public record HireConfirmation(
        ClientDTO client,
        Date dateIn,
        Date dateOut,
        String price,
        VehicleDTO vehicle
) {

}
