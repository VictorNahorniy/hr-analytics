package com.blago.hranalytics.controllers;

import com.blago.hranalytics.models.BuildingObject;
import com.blago.hranalytics.services.BuildingObjectService;
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
public class BuildingObjectController {
    @Autowired
    private BuildingObjectService buildingObjectService;

    @GetMapping("/create-build-obj")
    ModelAndView createBuildingObject() {
        ModelAndView modelAndView = new ModelAndView("building-object/add-building-obj");
        modelAndView.addObject("buildObj", new BuildingObject());
        modelAndView.addObject("buildingObjects", buildingObjectService.getAll());
        return modelAndView;
    }


    @PostMapping("/save-build-obj")
    public String addBuildingObject(@ModelAttribute BuildingObject buildingObject) {
        buildingObjectService.saveBuildingObject(buildingObject);
        return "redirect:/build-obj-list";
    }

    @GetMapping("/build-obj-list")
    ModelAndView getBuildingObjects() {
        return new ModelAndView("building-object/building-obj-list")
                .addObject("buildingObjects", buildingObjectService.getAll());
    }

    @GetMapping("/build-obj-profile")
    ModelAndView getBuildingObjectProfile(@RequestParam Integer id){
        return new ModelAndView("building-object/building-obj-profile")
                .addObject("buildObj", buildingObjectService.getById(id));
    }

    @GetMapping("/delete-build-obj")
    String deleteBuildingObject(@RequestParam Integer id){
        buildingObjectService.deleteById(id);
        return "redirect:/build-obj-list";
    }
}
