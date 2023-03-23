package com.blago.hranalytics.repositories;

import com.blago.hranalytics.models.NarcologistCertificate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface NarcologistCertificateRepository extends JpaRepository<NarcologistCertificate, Integer> {

    boolean existsByEmployeeId(Integer employeeId);

    List<NarcologistCertificate> findNarcologistCertificateByEmployeeIdAndExpirationGreaterThanEqual(Integer employeeId, Date date);

    List<NarcologistCertificate> findNarcologistCertificateByEmployeeId(Integer employeeId);
}
