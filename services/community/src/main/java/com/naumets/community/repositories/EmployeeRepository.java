package com.naumets.community.repositories;

import java.util.List;

import com.naumets.community.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	
	public Employee findByUsername(String un);


	Integer countAllBy();

    Integer countByFirstnameAndLastname(String firstname, String lastname);

	List<Employee> findByFirstnameAndLastname(String firstname, String lastname);
}
