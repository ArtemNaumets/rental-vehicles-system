package com.naumets.community.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document(collection = "persons")
public class Person {

    private String firstname;
    private String lastname;
    private String othername;

    private String socialSecurityNumber;
    private String gender;
    private String maritalStatus;

    private String countryid;

    private String stateid;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;

    private String city;
    private String address;
    private String phone;
    private String email;
}
