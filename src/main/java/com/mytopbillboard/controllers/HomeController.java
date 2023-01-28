package com.mytopbillboard.controllers;

import com.mytopbillboard.models.Playlist;
import com.mytopbillboard.models.User;
import com.mytopbillboard.repositories.PlaylistRepository;
import com.mytopbillboard.repositories.UserRepository;
import com.mytopbillboard.services.Utils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {

    private final UserRepository userDao;
    private final PlaylistRepository playlistDao;

    public HomeController(UserRepository userDao, PlaylistRepository playlistDao) {
        this.userDao = userDao;
        this.playlistDao = playlistDao;
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
        List<User> rankingList = new ArrayList<>();
        User testuser;
        for (int i = 0; i < users.size(); i++){
            testuser = users.get(i);
            for (int j = i; j < users.size(); j++){
                if (Utils.averageRating(testuser) < Utils.averageRating(users.get(j))){
                    testuser = users.get(j);
                }
            }
            rankingList.add(testuser);
            users.remove(testuser);
            i--;
        }        model.addAttribute("topRatedUsers", rankingList);
        //the following must be below "topratedusers" and is responsible for getting the list of userRatings
        List<Float> ratingnumber = new ArrayList<>();
        rankingList.forEach(user -> {
            ratingnumber.add(Utils.averageRating(user));
        });

        List<Playlist> allPlaylists = playlistDao.findAll();
        List<Playlist> newPlaylists = new ArrayList<>();
        for (int i = 1; i <= 5; i++){
            newPlaylists.add(allPlaylists.get(allPlaylists.size() - i));
        }
        model.addAttribute("newPlaylists", newPlaylists);
        model.addAttribute("userRatingsList", ratingnumber);
        model.addAttribute("users", firstFive);
        long userId = Utils.currentUserProfile();
        model.addAttribute("activeUser", userDao.findById(userId).getUsername());
        return "siteViews/homepage";
    }
}
