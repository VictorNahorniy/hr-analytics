package com.blago.hranalytics.repositories;

import com.blago.hranalytics.models.FiringType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FiringTypeRepository extends JpaRepository<FiringType, Integer> {
}
