package com.blago.hranalytics.services;

import com.blago.hranalytics.models.EmploymentType;
import com.blago.hranalytics.repositories.EmploymentTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmploymentTypeService {
    @Autowired
    private EmploymentTypeRepository employmentTypeRepository;

    public List<EmploymentType> getAll() {
        return employmentTypeRepository.findAll();
    }

    public String getNameById(Integer employmentTypeId) {
        return employmentTypeRepository.findEmploymentTypeNameById(employmentTypeId);
    }
}
