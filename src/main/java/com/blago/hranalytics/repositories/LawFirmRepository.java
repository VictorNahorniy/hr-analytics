package com.blago.hranalytics.repositories;

import com.blago.hranalytics.models.LawFirm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LawFirmRepository extends JpaRepository<LawFirm, Integer> {
}
