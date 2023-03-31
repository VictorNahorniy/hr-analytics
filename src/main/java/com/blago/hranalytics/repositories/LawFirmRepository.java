package com.blago.hranalytics.repositories;

import com.blago.hranalytics.models.LawFirm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LawFirmRepository extends JpaRepository<LawFirm, Integer> {
    @Query(value = "select lawFirmName from LawFirm where lawFirmId = ?1")
    String findLawFirmNameById(Integer lawFirmId);
}
