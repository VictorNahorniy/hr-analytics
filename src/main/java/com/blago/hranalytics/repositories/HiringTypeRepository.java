package com.blago.hranalytics.repositories;

import com.blago.hranalytics.models.HiringType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HiringTypeRepository extends JpaRepository<HiringType, Integer> {
    Optional<HiringType> findHiringTypeByNameType(String name);
}
