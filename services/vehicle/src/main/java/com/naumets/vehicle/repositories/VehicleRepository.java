package com.naumets.vehicle.repositories;

import com.naumets.vehicle.models.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Integer>{

    @Query("SELECT COUNT(vi.vehicletypeid) " +
            "FROM Vehicle vi " +
            "WHERE vi.vehicletypeid = ( " +
            "   SELECT vt.id " +
            "   FROM VehicleType vt " +
            "   WHERE vt.description = :description)")
    Integer countVehiclesByType(@Param("description") String description);
}
