package com.blago.hranalytics.controllers;

import com.blago.hranalytics.exceptions.EmployeeDepartmentException;
import com.blago.hranalytics.models.EmployeeDepartment;
import com.blago.hranalytics.services.EmployeeDepartmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
public class EmployeeDepartmentController {
    @Autowired
    private EmployeeDepartmentService employeeDepartmentService;

    @GetMapping("/department-create-form")
    ModelAndView getCreateForm() {
        return new ModelAndView("department/create-form")
                .addObject("employeeDepartment", new EmployeeDepartment())
                .addObject("departments", employeeDepartmentService.getAll());
    }

    @PostMapping("/save-department")
    ModelAndView saveDepartment(@ModelAttribute EmployeeDepartment employeeDepartment) {
        if (employeeDepartmentService.save(employeeDepartment)) {
            return new ModelAndView("department/create-form")
                    .addObject("employeeDepartment", new EmployeeDepartment())
                    .addObject("departments", employeeDepartmentService.getAll());
        }
        return new ModelAndView("department/create-form")
                .addObject("employeeDepartment", new EmployeeDepartment())
                .addObject("departments", employeeDepartmentService.getAll());
    }

    @GetMapping("/department-profile")
    ModelAndView getDepartmentProfile(@RequestParam Integer id) throws EmployeeDepartmentException {
        if (employeeDepartmentService.existsById(id)) {
            return new ModelAndView("department/profile")
                    .addObject("department", employeeDepartmentService.getById(id));
        }
        return new ModelAndView("department/create-form")
                .addObject("employeeDepartment", new EmployeeDepartment())
                .addObject("departments", employeeDepartmentService.getAll());
    }

    @PostMapping("/department-update")
    String updateDepartment(@ModelAttribute EmployeeDepartment employeeDepartment) {
        employeeDepartmentService.update(employeeDepartment);
        return "redirect:/department-profile?id=" + employeeDepartment.getEmployeeDepartmentId();
    }

    @GetMapping("/department-delete")
    String deleteDepartment(@RequestParam Integer id) {
        employeeDepartmentService.deleteById(id);
        return "redirect:/department-create-form";
    }
}
