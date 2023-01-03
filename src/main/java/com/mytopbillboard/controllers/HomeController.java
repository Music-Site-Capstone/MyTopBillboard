package com.mytopbillboard.controllers;

import com.mytopbillboard.repositories.UserRepository;
import com.mytopbillboard.services.Utils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final UserRepository userDao;

    public HomeController(UserRepository userDao) {
        this.userDao = userDao;
    }

    @GetMapping("/landingPage")
    public String welcome() {
        return "/siteViews/landing_page";
    }

    @GetMapping("/homepage")
    public String welcomeHome(Model model) {
        model.addAttribute("randomUserId", (int) Math.ceil(Math.random() * userDao.findAll().size()));
        // on html i will loop through the find all starting at the random number
        // loop will have links
        long userId = Utils.currentUserProfile();
        model.addAttribute("activeUser", userDao.findById(userId).getUsername());
        return "/siteViews/homepage";
    }
}
