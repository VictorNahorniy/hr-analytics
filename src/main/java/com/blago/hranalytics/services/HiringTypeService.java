package com.blago.hranalytics.services;

import com.blago.hranalytics.repositories.HiringTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HiringTypeService {
    @Autowired
    private HiringTypeRepository hiringTypeRepository;
}
