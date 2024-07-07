package com.naumets.locations.repositories;

import com.naumets.locations.models.Location;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface LocationRepository extends CrudRepository<Location, Integer> {

    Integer countAllBy();
}
