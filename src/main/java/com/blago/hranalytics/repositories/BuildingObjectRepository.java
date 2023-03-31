package com.blago.hranalytics.repositories;

import com.blago.hranalytics.models.BuildingObject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BuildingObjectRepository extends JpaRepository<BuildingObject, Integer> {
    @Query(value = "select objectName from BuildingObject where objectId = ?1")
    String findBuildingObjectNameById(Integer objectId);
}
