package com.naumets.community.repositories;

import java.util.List;

import com.naumets.community.models.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends MongoRepository<Employee, String> {

    Employee findByUsername(String username);

    long count();

    Long countByFirstnameAndLastname(String firstname, String lastname);

    List<Employee> findByFirstnameAndLastname(String firstname, String lastname);

}
