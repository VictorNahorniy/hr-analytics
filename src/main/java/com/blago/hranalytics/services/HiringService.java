package com.blago.hranalytics.services;

import com.blago.hranalytics.controllers.HiringController;
import com.blago.hranalytics.repositories.HiringRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HiringService {
    @Autowired
    private HiringRepository hiringRepository;
}
