package com.mytopbillboard.controllers;

import com.mytopbillboard.models.Playlist;
import com.mytopbillboard.models.User;
import com.mytopbillboard.repositories.PlaylistRepository;
import com.mytopbillboard.repositories.UserRepository;
import com.mytopbillboard.services.Utils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@Controller
public class UserController {

    private final UserRepository userDao;
    private final PlaylistRepository playlistDao;
    private final PasswordEncoder passwordEncoder;


    public UserController(UserRepository userDao, PlaylistRepository playlistDao, PasswordEncoder passwordEncoder){
        this.userDao = userDao;
        this.playlistDao = playlistDao;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/profile")
    public String profileRedirect(){
        return "siteViews/landing_page";
    }

    @GetMapping("/profile/{username}")
    public String usersProfile(Model model, @PathVariable("username") String username){
        long userId = Utils.currentUserProfile();
        model.addAttribute("pageOwner",userDao.findByUsername(username).getUsername());
        model.addAttribute("userID", userDao.findByUsername(username).getId());
        model.addAttribute("activeUser", userDao.findById(userId).getUsername());
        model.addAttribute("activeUserID", userId);
        model.addAttribute("allPlaylists", playlistDao.findAll());
        model.addAttribute("playlist", new Playlist());
        if(userDao.findByUsername(username) == null){
            return "redirect:/register";
        } else {
        return "siteViews/profile";
        }
    }


    @GetMapping("/register")
    public String showRegistrationForm(Model model){
        model.addAttribute("user", new User());
        return "/registration";
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
