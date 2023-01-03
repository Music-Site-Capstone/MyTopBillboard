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
        int randomUserId = (int) Math.ceil(Math.random() * userDao.findAll().size());
        for (int i = 0; i < 5; i++){
            if (randomUserId + i > userDao.findAll().size()){
                model.addAttribute("user" + i, userDao.findById(Math.abs(userDao.findAll().size() - (randomUserId + i))).getUsername());
            } else{
                model.addAttribute("user" + i, userDao.findById(randomUserId + i).getUsername());
            }
        }
        long userId = Utils.currentUserProfile();
        model.addAttribute("activeUser", userDao.findById(userId).getUsername());
        return "/siteViews/homepage";
    }
}
