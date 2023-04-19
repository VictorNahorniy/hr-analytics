package com.blago.hranalytics.controllers;

import com.blago.hranalytics.models.Firing;
import com.blago.hranalytics.services.EmployeeService;
import com.blago.hranalytics.services.FiringService;
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
public class FiringController {
    @Autowired
    private FiringService firingService;
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/create-firing")
    ModelAndView getFiringCreateForm(){
        return new ModelAndView("firing/create-form")
                .addObject("firing", new Firing())
                .addObject("employees", employeeService.getFirebleEmployees());
    }

    @PostMapping("/save-firing")
    ModelAndView saveFiring(@ModelAttribute Firing firing){
        if(firingService.isEmployeeFiredById(firing.getEmployeeId())){
            log.error("Error, employee is already fired");
            return new ModelAndView("firing/creating-error");
        }
        if(firingService.save(firing)){
            return new ModelAndView("redirect:/firing-list");
        }
        log.error("Error while saving firing");
        return new ModelAndView("firing/creating-error");
    }

    @GetMapping("/firing-list")
    ModelAndView getFiringList(){
        return new ModelAndView("firing/firing-list")
                .addObject("firingList", firingService.getAllDTO());

    }

    @PostMapping("/delete-firing")
    ModelAndView deleteFiring(@RequestParam Integer firingId){
        if(firingService.delete(firingId)){
            return new ModelAndView("redirect:/firing-list");
        }
        log.error("Error while deleting firing");
        return new ModelAndView("firing/deleting-error");
    }

    @GetMapping("/edit-firing")
    ModelAndView editFiring(@RequestParam Integer id){
        if(firingService.existById(id)){
            return new ModelAndView("firing/edit-firing")
                    .addObject("firing", firingService.getEntityById(id).get())
                    .addObject("employees", employeeService.getFirebleEmployees());
        }
        return new ModelAndView("firing/editing-error");
    }
}
