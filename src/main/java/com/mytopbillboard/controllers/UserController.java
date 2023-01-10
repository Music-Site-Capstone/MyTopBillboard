package com.mytopbillboard.controllers;

import com.mytopbillboard.models.Rating;
import com.mytopbillboard.models.Song;
import com.mytopbillboard.models.User;
import com.mytopbillboard.repositories.PlaylistRepository;
import com.mytopbillboard.repositories.RatingRepository;
import com.mytopbillboard.repositories.SongRepository;
import com.mytopbillboard.repositories.UserRepository;
import com.mytopbillboard.services.Utils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {

    private final UserRepository userDao;
    private final PlaylistRepository playlistDao;
    private final PasswordEncoder passwordEncoder;
    private final SongRepository songDao;


    public UserController(UserRepository userDao, PlaylistRepository playlistDao, PasswordEncoder passwordEncoder, SongRepository songDao){
        this.userDao = userDao;
        this.playlistDao = playlistDao;
        this.passwordEncoder = passwordEncoder;
        this.songDao = songDao;
    }

    @GetMapping("/profile")
    public String profileRedirect(){
        return "siteViews/landing_page";
    }

    @GetMapping("/profile/{username}")
    public String usersProfile(Model model, @PathVariable("username") String username){
        long userId = Utils.currentUserProfile();
        List<Song> songs = songDao.findAll();
        // the following is part of the check to see if a user has laready rated a playlist
        List<Long> playlistIdList = new ArrayList<>();
        userDao.findById(userId).getRatings().forEach(rating -> {
            playlistIdList.add(rating.getPlaylistId());
        });
        // the next 3 lines set up the rank to be displayed on the page
        List<User> users = userDao.findAll();
        users.sort((user1, user2) -> Math.round(Utils.averageRating(userDao.findByUsername(user1.getUsername()))) - Math.round(Utils.averageRating(userDao.findByUsername(user2.getUsername()))));
        model.addAttribute("rank", users.indexOf(userDao.findByUsername(username)) + 1);
        model.addAttribute("pageOwner",userDao.findByUsername(username).getUsername());
        model.addAttribute("userID", userDao.findByUsername(username).getId());
        model.addAttribute("activeUser", userDao.findById(userId).getUsername());
        model.addAttribute("activeUserID", userId);
        model.addAttribute("allPlaylists", playlistDao.findAll());
        model.addAttribute("songs", songs);
        model.addAttribute("averageRating", Utils.averageRating(userDao.findByUsername(username)));
        model.addAttribute("ratingCheck", playlistIdList);
        model.addAttribute("rating", new Rating());
        if(userDao.findByUsername(username) == null){
            return "redirect:/register";
        } else {
        return "siteViews/profile";
        }
    }


    @GetMapping("/register")
    public String showRegistrationForm(Model model){
        model.addAttribute("user", new User());
        return "registration";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user, HttpServletRequest request){
        String planPassword = user.getPassword();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDao.save(user);
        authWithHttpServletRequest(request, user.getUsername(), planPassword);
        return "redirect:/homepage";
    }

    private void authWithHttpServletRequest(HttpServletRequest request, String username, String password) {
        try {
            request.login(username, password);
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }

}
