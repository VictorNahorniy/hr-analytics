package com.blago.hranalytics.controllers;

import com.blago.hranalytics.models.Employee;
import com.blago.hranalytics.services.EmployeeService;
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
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/create-employee")
    ModelAndView createEmployee() {
        return new ModelAndView("employee/create-form")
                .addObject("newEmployee", new Employee());
    }

    @PostMapping("/save-employee")
    String addEmployee(@ModelAttribute Employee employee) {
        if (!employeeService.save(employee)) {
            log.error("Employee already exists");
            return "employee/creating-error";
        }
        return "redirect:/employee-list";
    }

    @GetMapping("/employee-list")
    ModelAndView getEmployees() {
        return new ModelAndView("employee/employee-list")
                .addObject("employees", employeeService.getAll());
    }

    @GetMapping("/edit-employee")
    ModelAndView editEmployee(@RequestParam Integer id) {
        if (employeeService.getById(id).isPresent()) {
            return new ModelAndView("employee/edit-form")
                    .addObject("employee", employeeService.getById(id).get());
        }
        log.error("Employee not found");
        return new ModelAndView("employee/editing-error");
    }

    @PostMapping("/update-employee")
    String updateEmployee(@ModelAttribute Employee employee) {
        if (!employeeService.update(employee)) {
            log.error("Employee not found");
            return "employee/editing-error";
        }
        return "redirect:/employee-list";
    }

    @GetMapping("/delete-employee")
    String deleteEmployee(@RequestParam Integer id) {
        employeeService.deleteById(id);
        return "redirect:/employee-list";
    }

    @GetMapping("/choose-parent")
    ModelAndView chooseParent() {
        return new ModelAndView("child/choose-parent")
                .addObject("employees", employeeService.getAll());
    }

    @GetMapping("/get-employee-list")
    ModelAndView getEmployeeList() {
        return new ModelAndView("employee/employee-list")
                .addObject("employees", employeeService.getAll());
    }
}
