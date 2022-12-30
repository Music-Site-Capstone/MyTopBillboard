package com.mytopbillboard.controllers;

import com.mytopbillboard.models.User;
import com.mytopbillboard.repositories.UserRepository;
import com.mytopbillboard.services.Utils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@Controller
public class UserController {

    private final UserRepository userDao;



    private final PasswordEncoder passwordEncoder;



    public UserController(UserRepository userDao, PasswordEncoder passwordEncoder){
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/profile")
    public String profileRedirect(){
        return "siteViews/landing_page";
    }

    @GetMapping("/profile/{username}")
    public String usersProfile(Model model, @PathVariable("username") String username){
        User currentUser = Utils.currentUser();
        long usersId = Utils.currentUserProfile();
        String currentUsersUsername = Utils.currentUsersUsername();
        model.addAttribute("usersId", userDao.findById(usersId));
        model.addAttribute("activeUser", currentUser);
        model.addAttribute("username", currentUsersUsername);
        if(currentUsersUsername != null){
            return "siteViews/profile";
        }
        return "redirect:/register";
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
        return "redirect:/profile/{username}";
    }

    private void authWithHttpServletRequest(HttpServletRequest request, String username, String password) {
        try {
            request.login(username, password);
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }

}
