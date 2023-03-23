package com.blago.hranalytics.repositories;

import com.blago.hranalytics.models.EmploymentLevel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmploymentLevelRepository extends JpaRepository<EmploymentLevel, Integer> {
}
