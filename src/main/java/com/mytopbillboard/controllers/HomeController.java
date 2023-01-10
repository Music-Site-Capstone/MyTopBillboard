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

    @GetMapping("/")
    public String welcome() {
        return "siteViews/landing_page";
    }


    @GetMapping("/homepage")
    public String welcomeHome(Model model) {

        List<User> users = userDao.findAll();

        //The following is a randomizer that finds 5 users to be populated into the discover section.
        int randomUserId = (int) Math.ceil(Math.random() * userDao.findAll().size());
        List<User> firstFive = new ArrayList<>();
        for (int i = 0; i < 5; i++){
            if (randomUserId + i > userDao.findAll().size()){
                firstFive.add(userDao.findAll().get(Math.abs(userDao.findAll().size() - (randomUserId + i -1))));
            } else{
                firstFive.add(userDao.findAll().get(randomUserId + i - 1));
            }
        }

        // The following contains the logic for the leaderboard and organizing user ranks. creates a sorted list of all users.
        //consider moving to utils to also use with profile.
        users.sort((user2, user1) -> Math.round(Utils.averageRating(userDao.findByUsername(user1.getUsername()))) - Math.round(Utils.averageRating(userDao.findByUsername(user2.getUsername()))));


        model.addAttribute("topRatedUsers", users);
        model.addAttribute("users", firstFive);
        long userId = Utils.currentUserProfile();
        model.addAttribute("activeUser", userDao.findById(userId).getUsername());
        return "siteViews/homepage";
    }
}
