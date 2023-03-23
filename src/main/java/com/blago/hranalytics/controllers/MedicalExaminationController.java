package com.blago.hranalytics.controllers;

import com.blago.hranalytics.services.MedicalExaminationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
@Slf4j
public class MedicalExaminationController {

    @Autowired
    MedicalExaminationService medicalExaminationService;


}
