package com.blago.hranalytics.services;

import com.blago.hranalytics.models.PsychiatristCertificate;
import com.blago.hranalytics.repositories.PsychiatristCertificateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PsychiatristCertificateService {

    @Autowired
    private PsychiatristCertificateRepository psychiatristCertificateRepository;

    public List<PsychiatristCertificate> getValidPsychiatristCertificatesByEmployeeId(Integer employeeId) {
        return psychiatristCertificateRepository.
                findPsychiatristCertificateByEmployeeIdAndExpirationGreaterThanEqual(employeeId, new java.util.Date());
    }

    public boolean save(PsychiatristCertificate psychiatristCertificate) {
        if (isPsychiatristCertificateDataNull(psychiatristCertificate)) {
            return false;
        }
        psychiatristCertificateRepository.save(psychiatristCertificate);
        return true;
    }

    private boolean isPsychiatristCertificateDataNull(PsychiatristCertificate psychiatristCertificate) {
        return psychiatristCertificate.getEmployeeId() == null || psychiatristCertificate.getExpiration() == null;
    }

    public List<PsychiatristCertificate> getAllByEmployeeId(Integer employeeId) {
        return psychiatristCertificateRepository.findPsychiatristCertificateByEmployeeId(employeeId);
    }

    public Optional<PsychiatristCertificate> getById(Integer id) {
        return psychiatristCertificateRepository.findById(id);
    }

    public boolean update(PsychiatristCertificate psychiatristCertificate) {
        if (isPsychiatristCertificateDataNull(psychiatristCertificate)) {
            return false;
        }
        psychiatristCertificateRepository.save(psychiatristCertificate);
        return true;
    }

    public boolean delete(Integer id) {
        if (psychiatristCertificateRepository.existsById(id)) {
            psychiatristCertificateRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
