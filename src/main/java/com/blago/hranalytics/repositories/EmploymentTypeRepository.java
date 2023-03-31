package com.blago.hranalytics.repositories;

import com.blago.hranalytics.models.EmploymentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EmploymentTypeRepository extends JpaRepository<EmploymentType, Integer> {
    @Query(value = "select name from EmploymentType where id = ?1")
    String findEmploymentTypeNameById(Integer employmentTypeId);
}
