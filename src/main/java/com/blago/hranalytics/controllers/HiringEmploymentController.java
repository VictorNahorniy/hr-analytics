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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.sql.SQLException;

@Controller
@Slf4j
public class HiringController {
    private static final String hiringTypeName = "Прийняття";
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

    @GetMapping("/create-hiring")
    ModelAndView createHiring() throws SQLException {
        Hiring newHiring = hiringService.createHiring();
        HiringType hiringType = hiringTypeService.getByName(hiringTypeName);
        newHiring.setHiringTypeId(hiringType.getTypeId());
        ModelAndView modelAndView = new ModelAndView("hiring/create-form")
                .addObject("hiring", newHiring);
        return getFilledHiring(modelAndView);
    }

    @PostMapping("/save-hiring")
    ModelAndView saveHiring(@ModelAttribute Hiring hiring) {
        if (hiringService.isEmployeeHired(hiring.getEmployeeId())) {
            return new ModelAndView("hiring/create-form-error");
        }
        hiringService.save(hiring);
        return new ModelAndView("redirect:/hiring-list");
    }

    @GetMapping("/hiring-list")
    ModelAndView getHiringList() {
        return new ModelAndView("hiring/list")
                .addObject("hiringList", hiringService.getAllDTO());
    }

    @GetMapping("/delete-hiring")
    ModelAndView deleteHiring(@RequestParam Integer hiringId) {
        hiringService.delete(hiringId);
        return new ModelAndView("redirect:/hiring-list");
    }

    @GetMapping("/edit-hiring")
    ModelAndView editHiring(@RequestParam Integer hiringId) {
        if (hiringService.getHiringById(hiringId).isPresent()) {
            ModelAndView modelAndView = new ModelAndView("hiring/edit-form")
                    .addObject("hiring", hiringService.getHiringById(hiringId).get());
            return getFilledHiring(modelAndView);
        }
        log.error("Hiring was not found by id");
        return new ModelAndView("hiring/search-error");
    }

    @PostMapping("/update-hiring")
    String updateHiring(@ModelAttribute Hiring hiring){
        if (hiringService.exists(hiring)){
            hiringService.update(hiring);
            return "redirect:/edit-hiring?hiringId=" + hiring.getHiringId();
        }
            return "hiring/search-error";
    }

    private ModelAndView getFilledHiring(ModelAndView modelAndView) {
        return modelAndView
                .addObject("employees", employeeService.getAll())
                .addObject("jobPositions", jobPositionService.getAll())
                .addObject("employeeDepartments", employeeDepartmentService.getAll())
                .addObject("buildingObjects", buildingObjectService.getAll())
                .addObject("lawFirms", lawFirmService.findAll())
                .addObject("employmentTypes", employmentTypeService.getAll())
                .addObject("hiringTypes", hiringTypeService.findAll());
    }
}
