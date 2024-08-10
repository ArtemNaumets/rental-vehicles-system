package com.naumets.vehicleparameters.repositories;

import com.naumets.vehicleparameters.models.VehicleModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface VehicleModelRepository extends JpaRepository<VehicleModel, Integer> {

}
