package com.mytopbillboard.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @GetMapping("/profile")
    public String profile(){
        return "siteViews/profile";
    }
}