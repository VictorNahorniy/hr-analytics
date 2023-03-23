package com.blago.hranalytics.services;

import com.blago.hranalytics.exceptions.NarcologistCertificateException;
import com.blago.hranalytics.models.NarcologistCertificate;
import com.blago.hranalytics.repositories.NarcologistCertificateRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class NarcologistCertificateService {
    @Autowired
    private NarcologistCertificateRepository repository;

    public List<NarcologistCertificate> getValidCertificatesByEmployeeId(Integer employeeId) {
        if (repository.existsByEmployeeId(employeeId)) {
            return repository.findNarcologistCertificateByEmployeeIdAndExpirationGreaterThanEqual(employeeId, new java.util.Date());
        }
        log.warn("Employee with id " + employeeId + " does not have a narcologist certificate");
        return new ArrayList<>();
    }

    public boolean save(NarcologistCertificate narcologistCertificate) {
        if (isNarcologistCertificateDataNull(narcologistCertificate)) {
            throw new NarcologistCertificateException("Narcologist certificate data is null");
        }
        repository.save(narcologistCertificate);
        return true;
    }

    private boolean isNarcologistCertificateDataNull(NarcologistCertificate narcologistCertificate) {
        return narcologistCertificate.getEmployeeId() == null || narcologistCertificate.getExpiration() == null;
    }

    public List<NarcologistCertificate> getAllByEmployeeId(Integer employeeId) {
        return repository.findNarcologistCertificateByEmployeeId(employeeId);
    }

    public Optional<NarcologistCertificate> getById(Integer id) {
        return repository.findById(id);
    }

    public boolean update(NarcologistCertificate narcologistCertificate) {
        if (isNarcologistCertificateDataNull(narcologistCertificate)) {
            throw new NarcologistCertificateException("Narcologist certificate data is null");
        }
        repository.save(narcologistCertificate);
        return true;
    }

    public boolean delete(Integer id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}
