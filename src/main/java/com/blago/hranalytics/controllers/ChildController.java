package com.blago.hranalytics.controllers;

import com.blago.hranalytics.models.Child;
import com.blago.hranalytics.services.ChildService;
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
public class ChildController {
    @Autowired
    private ChildService childService;

    @Autowired
    private EmployeeService employeeService;


    @GetMapping("/create-child")
    ModelAndView createChild(@RequestParam Integer parentId) {
        Child child = new Child();
        child.setEmployeeId(parentId);
        if (employeeService.getFullNameById(parentId).isPresent()) {
            String employeeName = employeeService.getFullNameById(parentId).get();
            return new ModelAndView("child/create-form")
                    .addObject("newChild", child)
                    .addObject("parentName", employeeName)
                    .addObject("children", childService.getChildrenByParentId(parentId));
        }
        log.error("Employee not found");
        return new ModelAndView("child/creating-error");
    }

    @PostMapping("/save-child")
    String addChild(@ModelAttribute Child child) {
        if (!childService.save(child)) {
            log.error("Child already exists");
            return "child/creating-error";
        }
        return "redirect:/create-child?parentId=" + child.getEmployeeId();
    }

    @GetMapping("/get-child-list")
    ModelAndView getChildList(@RequestParam Integer parentId) {
        if (employeeService.getFullNameById(parentId).isPresent()) {
            String employeeName = employeeService.getFullNameById(parentId).get();
            return new ModelAndView("child/child-list")
                    .addObject("parentName", employeeName)
                    .addObject("children", childService.getChildrenByParentId(parentId));
        }
        log.error("Employee not found");
        return new ModelAndView("child/creating-error");
    }

    @GetMapping("/child-profile")
    ModelAndView getChildProfile(@RequestParam Integer childId) {
        if (childService.getById(childId).isPresent()) {
            return new ModelAndView("child/child-profile")
                    .addObject("child", childService.getById(childId).get());
        }
        log.error("Employee or child not found");
        return new ModelAndView("child/creating-error");
    }

    @GetMapping("/delete-child")
    String deleteChild(@RequestParam Integer childId) {
        Integer parentId = null;
        if (childService.getById(childId).isPresent()) {
            parentId = childService.getById(childId).get().getEmployeeId();
            childService.deleteById(childId);
        }
        if (parentId != null) {
            return "redirect:/create-child?parentId=" + parentId;
        }
        log.error("Employee or child not found");
        return "child/deleting-error";
    }

    @PostMapping("/update-child")
    String updateChild(@ModelAttribute Child child) {
        if (childService.getById(child.getChildId()).isPresent()) {
            childService.save(child);
        }
        return "redirect:/child-profile?childId=" + child.getChildId();
    }
}
