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

@Slf4j
@Controller
public class HiringTransferController {
    private static final String hiringTypeName = "Переведення";
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

    @GetMapping("/create-transfer")
    ModelAndView createTransfer() throws SQLException {
        Hiring transfer = hiringService.createHiring();
        HiringType hiringType = hiringTypeService.getByName(hiringTypeName);
        transfer.setHiringTypeId(hiringType.getTypeId());
        ModelAndView modelAndView = new ModelAndView("transfer/create-form")
                .addObject("transfer", transfer);
        return getFilledHiring(modelAndView);
    }

    @PostMapping("/save-transfer")
    ModelAndView saveTransfer(@ModelAttribute Hiring hiring) throws SQLException {
        if (hiringService.isEmployeeHired(hiring.getEmployeeId())) {
            hiringService.saveTransfer(hiring);
            return new ModelAndView("redirect:/transfer-list");
        }
        return new ModelAndView("transfer/create-form-error");
    }

    @GetMapping("/transfer-list")
    ModelAndView getTransferList() throws SQLException {
        return new ModelAndView("transfer/transfer-list")
                .addObject("hiringList", hiringService.getAllTransfersDTO());
    }

    @PostMapping("/delete-transfer")
    ModelAndView deleteTransfer(@RequestParam Integer id) {
        if (hiringService.isPreviousHiring(id)) {
            return new ModelAndView("transfer/error-deleting");
        }
        hiringService.deleteById(id);
        return new ModelAndView("redirect:/transfer-list");
    }

    @GetMapping("/edit-transfer")
    ModelAndView getEditTransferForm(@RequestParam Integer hiringId) {
        if (hiringService.getHiringById(hiringId).isPresent()) {
            ModelAndView modelAndView = new ModelAndView("hiring/edit-form")
                    .addObject("hiring", hiringService.getHiringById(hiringId).get());
            return getFilledHiring(modelAndView);
        }
        log.error("Hiring was not found by id");
        return new ModelAndView("transfer/search-error");
    }

    @PostMapping("/update-transfer")
    String updateHiring(@ModelAttribute Hiring hiring) {
        if (hiringService.exists(hiring)) {
            hiringService.update(hiring);
            return "redirect:/edit-transfer?hiringId=" + hiring.getHiringId();
        }
        return "transfer/search-error";
    }

    @GetMapping("/employee-transfer-list")
    ModelAndView getEmployeeTransfers(@RequestParam Integer employeeId) {
        if (hiringService.existByEmployeeId(employeeId)) {
            return new ModelAndView("transfer/employee-transfers")
                    .addObject("hiringList", hiringService.getTransfersDTOByEmployeeId(employeeId));
        }
        return new ModelAndView("transfer/empty-transfer-list")
                .addObject("employee", employeeService.getById(employeeId).get());
    }

    private ModelAndView getFilledHiring(ModelAndView modelAndView) {
        return modelAndView
                .addObject("employees", employeeService.getHiredEmployees())
                .addObject("jobPositions", jobPositionService.getAll())
                .addObject("employeeDepartments", employeeDepartmentService.getAll())
                .addObject("buildingObjects", buildingObjectService.getAll())
                .addObject("lawFirms", lawFirmService.findAll())
                .addObject("employmentTypes", employmentTypeService.getAll())
                .addObject("hiringTypes", hiringTypeService.findAll());
    }
}
