package com.blago.hranalytics.services;

import com.blago.hranalytics.models.EmploymentLevel;
import com.blago.hranalytics.repositories.EmploymentLevelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmploymentLevelService {
    @Autowired
    private EmploymentLevelRepository employmentLevelRepository;

    public List<EmploymentLevel> findAll() {
        return employmentLevelRepository.findAll();
    }

    public EmploymentLevel findById(Integer employmentLevelId) {
        return employmentLevelRepository.findById(employmentLevelId).orElse(null);
    }
}
