package com.naumets.hrconfigurations.repositories;

import com.naumets.hrconfigurations.models.JobTitle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface JobTitleRepository extends JpaRepository<JobTitle, Integer> {

}
