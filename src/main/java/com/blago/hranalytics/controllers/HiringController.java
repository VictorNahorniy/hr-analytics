package com.blago.hranalytics.controllers;

import com.blago.hranalytics.models.Hiring;
import com.blago.hranalytics.models.HiringType;
import com.blago.hranalytics.services.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.sql.SQLException;

@Controller
@Slf4j
public class HiringController {
    @Autowired
    private HiringService hiringService;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private JobPositionService jobPositionService;
    @Autowired
    private EmployeeDepartmentService employeeDepartmentService;
    @Autowired
    private BuildingObjectService buildingObjectService;
    @Autowired
    private LawFirmService lawFirmService;
    @Autowired
    private EmploymentTypeService employmentTypeService;
    @Autowired
    private HiringTypeService hiringTypeService;
    private static final String hiringTypeName = "Прийняття";

    @GetMapping("/create-hiring")
    ModelAndView createHiring() throws SQLException {
        Hiring newHiring = hiringService.createHiring();
        HiringType hiringType = hiringTypeService.getByName(hiringTypeName);
        newHiring.setHiringTypeId(hiringType.getTypeId());
        return new ModelAndView("hiring/create-form")
                .addObject("hiring", newHiring)
                .addObject("employees", employeeService.getAll())
                .addObject("jobPositions", jobPositionService.getAll())
                .addObject("employeeDepartments", employeeDepartmentService.getAll())
                .addObject("buildingObjects", buildingObjectService.getAll())
                .addObject("lawFirms", lawFirmService.findAll())
                .addObject("employmentTypes", employmentTypeService.getAll())
                .addObject("hiringTypes", hiringTypeService.findAll());
    }

    @PostMapping("/save-hiring")
    ModelAndView saveHiring(@ModelAttribute Hiring hiring) {
        if(hiringService.save(hiring)){
            return new ModelAndView("redirect:/hiring-list");
        }
        return new ModelAndView("hiring/create-form-error");
    }

    @GetMapping("/hiring-list")
    ModelAndView getHiringList(){
        return new ModelAndView("hiring/list")
                .addObject("hiringList", hiringService.getAllDTO());
    }
}
