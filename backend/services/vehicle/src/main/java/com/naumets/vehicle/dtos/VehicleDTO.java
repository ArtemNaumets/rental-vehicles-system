package com.naumets.vehicle.dtos;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehicleDTO{

    private int id;

    private String name;

    private Integer vehicletypeid;

    private String vehicleNumber;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date registrationDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date acquisitionDate;
    private String description;

    private Integer vehiclemakeid;

    private String power;
    private String fuelCapacity;

    private Integer vehiclestatusid;

    private String netWeight;

    private String employeeid;

    private Integer vehiclemodelid;

    private Integer locationid;

    private String remarks;
}
