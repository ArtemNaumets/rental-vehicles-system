package com.naumets.vehiclemanagementservice.repositories;

import com.naumets.vehiclemanagementservice.models.VehicleInformation;
import com.naumets.vehiclemanagementservice.models.vehicle.VehicleInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface VehicleRepository extends JpaRepository<VehicleInformation, Integer>{

    @Query("SELECT COUNT(vi.vehicletypeid) " +
            "FROM VehicleInformation vi " +
            "WHERE vi.vehicletypeid = ( " +
            "   SELECT vt.id " +
            "   FROM VehicleType vt " +
            "   WHERE vt.description = :description)")
    Integer countVehiclesByType(@Param("description") String description);
}
