package com.blago.hranalytics.repositories;

import com.blago.hranalytics.models.BuildingObject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BuildingObjectRepository extends JpaRepository<BuildingObject, Integer> {
}
