package com.naumets.vehiclemanagementservice.repositories;

import com.naumets.vehiclemanagementservice.models.VehicleMovement;
import com.naumets.vehiclemanagementservice.models.vehicle.VehicleMovement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface VehicleMovementRepository extends JpaRepository<VehicleMovement, Integer> {

}
