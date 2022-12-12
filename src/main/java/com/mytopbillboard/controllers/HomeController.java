package com.mytopbillboard.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/landingPage")
    public String welcome() {
        return "/siteViews/landing_page";
    }

    @GetMapping("/homepage")
    public String welcomeHome() {
        return "/siteViews/homepage";
    }
}
