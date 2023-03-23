package com.blago.hranalytics.repositories;

import com.blago.hranalytics.models.PsychiatristCertificate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface PsychiatristCertificateRepository extends JpaRepository<PsychiatristCertificate, Integer> {

    List<PsychiatristCertificate> findPsychiatristCertificateByEmployeeIdAndExpirationGreaterThanEqual(Integer employeeId, Date expiration);

    boolean existsByEmployeeId(Integer employeeId);

    List<PsychiatristCertificate> findPsychiatristCertificateByEmployeeId(Integer employeeId);
}
