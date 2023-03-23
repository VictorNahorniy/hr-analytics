package com.blago.hranalytics.controllers;

import com.blago.hranalytics.models.LawFirm;
import com.blago.hranalytics.services.LawFirmService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
public class LawFirmController {
    @Autowired
    private LawFirmService lawFirmService;

    @GetMapping("/create-law-firm")
    ModelAndView createLawFirm() {
        return new ModelAndView("law-firm/create-form")
                .addObject("lawFirm", new LawFirm())
                .addObject("lawFirms", lawFirmService.findAll());
    }

    @PostMapping("/save-law-firm")
    ModelAndView saveLawFirm(LawFirm lawFirm) {
        lawFirmService.save(lawFirm);
        return new ModelAndView("redirect:/create-law-firm");
    }

    @GetMapping("/law-firm-profile")
    ModelAndView lawFirmProfile(@RequestParam Integer id) {
        return new ModelAndView("law-firm/profile")
                .addObject("lawFirm", lawFirmService.findById(id));
    }

    @PostMapping("update-law-firm")
    ModelAndView updateLawFirm(LawFirm lawFirm) {
        lawFirmService.save(lawFirm);
        return new ModelAndView("redirect:/law-firm-profile?id=" + lawFirm.getLawFirmId());
    }

    @GetMapping("/delete-law-firm")
    ModelAndView deleteLawFirm(@RequestParam Integer id) {
        lawFirmService.deleteById(id);
        return new ModelAndView("redirect:/create-law-firm");
    }
}
