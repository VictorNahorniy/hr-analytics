package com.blago.hranalytics.repositories;

import com.blago.hranalytics.models.Firing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FiringRepository extends JpaRepository<Firing, Integer> {
    boolean existsByEmployeeId(Integer employeeId);
}
