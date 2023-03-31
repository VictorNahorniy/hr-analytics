package com.blago.hranalytics.controllers;

import com.blago.hranalytics.models.JobPosition;
import com.blago.hranalytics.services.EmploymentLevelService;
import com.blago.hranalytics.services.JobCategoryService;
import com.blago.hranalytics.services.JobPositionService;
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
public class JobPositionController {
    @Autowired
    private JobPositionService jobPositionService;

    @Autowired
    private JobCategoryService jobCategoryService;

    @Autowired
    private EmploymentLevelService employmentLevelService;

    @GetMapping("/create-job-position")
    public ModelAndView createJobPosition() {
        return new ModelAndView("job-position/create-form")
                .addObject("jobCategories", jobCategoryService.findAll())
                .addObject("employmentLevels", employmentLevelService.findAll())
                .addObject("jobPosition", new JobPosition());
    }

    @PostMapping("/save-job-position")
    public String saveJobPosition(@ModelAttribute JobPosition jobPosition) {
        if(jobPositionService.save(jobPosition)){
            return "redirect:/job-positions";
        }
        return "redirect:/error";
    }

    @GetMapping("/job-positions")
    public ModelAndView jobPositions() {
        return new ModelAndView("job-position/job-position-list")
                .addObject("jobPositions", jobPositionService.getAllDTO());
    }

    @GetMapping("/job-position-profile")
    public ModelAndView jobPositionsProfile(@RequestParam Integer id) {
        if (jobPositionService.findById(id).isPresent()) {
            return new ModelAndView("job-position/profile")
                    .addObject("jobPosition", jobPositionService.findById(id).get())
                    .addObject("jobCategories", jobCategoryService.findAll())
                    .addObject("employmentLevels", employmentLevelService.findAll());
        }
        return new ModelAndView("redirect:/error");
    }

    @GetMapping("/delete-job-position")
    public String deleteJobPosition(@RequestParam Integer id) {
        if (jobPositionService.findById(id).isPresent()) {
            jobPositionService.deleteById(id);
            return "redirect:/job-positions";
        }
        return "redirect:/error";
    }

    @PostMapping("/update-job-position")
    public String updateJobPosition(@ModelAttribute JobPosition jobPosition) {
        if (jobPositionService.findById(jobPosition.getJobPositionId()).isPresent()) {
            jobPositionService.update(jobPosition);
            return "redirect:/job-positions";
        }
        return "redirect:/error";
    }
}
