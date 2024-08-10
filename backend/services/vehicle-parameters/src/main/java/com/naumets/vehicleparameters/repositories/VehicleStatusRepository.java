package com.naumets.vehicleparameters.repositories;

import com.naumets.vehicleparameters.models.VehicleStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface VehicleStatusRepository extends JpaRepository<VehicleStatus, Integer> {

}
