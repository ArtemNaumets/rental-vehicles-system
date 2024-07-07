package com.naumets.locations.repositories;

import com.naumets.locations.models.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface StateRepository extends JpaRepository<State, Integer> {

}
