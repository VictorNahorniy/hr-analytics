package com.blago.hranalytics.repositories;

import com.blago.hranalytics.models.JobPosition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface JobPositionRepository extends JpaRepository<JobPosition, Integer> {
    JobPosition findByPositionName(String positionName);
    @Query(value = "select positionName from JobPosition where jobPositionId = ?1")
    String findJobPositionNameById(Integer id);
}
