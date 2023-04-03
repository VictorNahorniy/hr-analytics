package com.blago.hranalytics.repositories;

import com.blago.hranalytics.models.Hiring;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HiringRepository extends JpaRepository<Hiring, Integer> {
    Hiring findHiringByEmployeeId(Integer id);

    @Query(value = "SELECT h " +
            "from Hiring h " +
            "where h.employeeId = ?1 " +
            "and h.hiringDate = (select max(h2.hiringDate) " +
            "from Hiring h2 where h2.employeeId = ?1)")
    Hiring findLastHiringByEmployeeId(Integer employeeId);

    List<Hiring> findHiringByHiringTypeId(Integer id);

    boolean existsHiringByEmployeeId(Integer id);
}
