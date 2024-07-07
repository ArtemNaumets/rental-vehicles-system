package com.naumets.hrconfigurations.repositories;

import com.naumets.hrconfigurations.models.EmployeeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EmployeeTypeRepository extends JpaRepository<EmployeeType, Integer> {

}
