package com.blago.hranalytics.repositories;

import com.blago.hranalytics.models.JobPosition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobPositionRepository extends JpaRepository<JobPosition, Integer> {
    JobPosition findByPositionName(String positionName);
}
