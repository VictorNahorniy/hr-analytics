package com.blago.hranalytics.repositories;

import com.blago.hranalytics.models.MedicalExamination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface MedicalExaminationRepository extends JpaRepository<MedicalExamination, Integer> {
    List<MedicalExamination> findMedicalExaminationsByEmployeeIdAndAndExpirationGreaterThanEqual(Integer employeeId, Date expiration);

    List<MedicalExamination> findMedicalExaminationsByEmployeeId(Integer employeeId);
}
