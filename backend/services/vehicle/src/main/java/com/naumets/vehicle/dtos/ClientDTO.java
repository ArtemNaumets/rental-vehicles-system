package com.naumets.vehicle.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientDTO{

    private String id;
    private String name;
    private String address;
    private String city;
    private String phone;
    private String mobile;
    private String website;
    private String email;
    private String countryid;
    private String stateid;
    private String details;
}
