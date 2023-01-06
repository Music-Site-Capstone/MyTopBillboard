package com.mytopbillboard.controllers;

import com.mytopbillboard.models.Playlist;
import com.mytopbillboard.models.Rating;
import com.mytopbillboard.models.Song;
import com.mytopbillboard.models.User;
import com.mytopbillboard.repositories.PlaylistRepository;
import com.mytopbillboard.repositories.SongRepository;
import com.mytopbillboard.repositories.UserRepository;
import com.mytopbillboard.services.Utils;
import org.springframework.lang.Nullable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
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
    public String usersProfile(Model model, @PathVariable("username") String username, @Nullable @RequestParam(value = "playlistId", required = false, defaultValue = "1") Long playListId){
        long userId = Utils.currentUserProfile();
        List<Song> songs = songDao.findAll();
        model.addAttribute("pageOwner",userDao.findByUsername(username).getUsername());
        model.addAttribute("userID", userDao.findByUsername(username).getId());
        model.addAttribute("activeUser", userDao.findById(userId).getUsername());
        model.addAttribute("activeUserID", userId);
        model.addAttribute("allPlaylists", playlistDao.findAll());
        model.addAttribute("singlePlaylistId", playlistDao.findById(playListId));
        model.addAttribute("singlePlaylistName", playlistDao.findById(playListId).get().getPlaylistName());
        model.addAttribute("songs", songs);
        model.addAttribute("averageRating", Utils.averageRating(userDao.findByUsername(username)));
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
