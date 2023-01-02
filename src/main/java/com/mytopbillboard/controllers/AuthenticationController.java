package com.mytopbillboard.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import static org.hibernate.tool.schema.SchemaToolingLogging.LOGGER;

@Controller
public class AuthenticationController {

    @GetMapping("/login")
    public String showLoginPage(){
        return "/login";
    }



}
