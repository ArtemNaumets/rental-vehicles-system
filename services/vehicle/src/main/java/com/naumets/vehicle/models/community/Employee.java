package com.naumets.vehicle.models.community;

import java.util.Date;

import com.naumets.vehicle.models.hrConfiguration.EmployeeType;
import com.naumets.vehicle.models.hrConfiguration.JobTitle;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper=false)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Employee extends Person {
		
	@ManyToOne
	@JoinColumn(name="employeeTypeId", insertable=false, updatable=false)
	private EmployeeType employeeType;
	private Integer employeeTypeId;
	private String photo;
	private String username;
	
	@ManyToOne
	@JoinColumn(name="jobTitleId", insertable=false, updatable=false)
	private JobTitle jobTitle;
	private Integer jobTitleId;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")	
	private Date hireDate;


}
