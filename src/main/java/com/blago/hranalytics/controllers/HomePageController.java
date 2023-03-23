package com.blago.hranalytics.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class HomePageController {

    @GetMapping({"/home", "/"})
    public String home(){
        return "home-page";
    }
}
