package com.blago.hranalytics.services;

import com.blago.hranalytics.models.EmploymentType;
import com.blago.hranalytics.repositories.EmploymentTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmploymentTypeService {
    @Autowired
    private EmploymentTypeRepository employmentTypeRepository;
}
