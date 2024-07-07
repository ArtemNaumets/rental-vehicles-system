package com.naumets.vehiclemanagementservice.repositories;

import com.naumets.vehiclemanagementservice.models.VehicleMaintenance;
import com.naumets.vehiclemanagementservice.models.vehicle.VehicleHire;
import com.naumets.vehiclemanagementservice.models.vehicle.VehicleMaintenance;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface VehicleMaintenanceRepository extends JpaRepository<VehicleMaintenance, Integer>{


    default List<VehicleMaintenance> findLast10() {
        Pageable pageable = PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "id"));
        return findAll(pageable).getContent();
    }
}
