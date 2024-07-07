package com.naumets.vehiclemanagementservice.repositories;

import com.naumets.vehiclemanagementservice.models.VehicleHire;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface VehicleHireRepository extends JpaRepository<VehicleHire, Integer> {

    default List<VehicleHire> findLast10() {
        Pageable pageable = PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "id"));
        return findAll(pageable).getContent();
    }

}
