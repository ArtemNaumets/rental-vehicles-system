package com.naumets.community.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper=false)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Document(collection = "employees")
public class Employee extends Person {

    @Id
    private String id;

    private String employeeTypeId;

    private String photo;
    private String username;

    private String jobTitleId;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate hireDate;

}
