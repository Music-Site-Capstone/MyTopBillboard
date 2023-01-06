package com.mytopbillboard.controllers;

import com.mytopbillboard.models.User;
import com.mytopbillboard.repositories.UserRepository;
import com.mytopbillboard.services.Utils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Controller
public class HomeController {

    private final UserRepository userDao;

    public HomeController(UserRepository userDao) {
        this.userDao = userDao;
    }

    @GetMapping("/landingPage")
    public String welcome() {
        return "siteViews/landing_page";
    }


    @GetMapping("/homepage")
    public String welcomeHome(Model model) {

        List<User> users = userDao.findAll();
        List<User> firstFive = new ArrayList<>();
        for (int i = 0; i < 5; i++){
            firstFive.add(users.get(i));
        }

        Collections.sort(users, new Comparator<User>() {
            public int compare(User user1, User user2){
                return Math.round(Utils.averageRating(userDao.findByUsername(user1.getUsername()))) - Math.round(Utils.averageRating(userDao.findByUsername(user2.getUsername())));
            }
        });
        for(User user : users){
            System.out.println(user.getUsername());
        }

        model.addAttribute("topRatedUsers", users);
        model.addAttribute("users", firstFive);
        long userId = Utils.currentUserProfile();
        model.addAttribute("activeUser", userDao.findById(userId).getUsername());
        return "siteViews/homepage";
    }
}
