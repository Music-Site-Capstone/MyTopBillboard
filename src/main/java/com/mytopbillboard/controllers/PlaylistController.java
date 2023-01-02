package com.mytopbillboard.controllers;

import com.mytopbillboard.models.Playlist;
import com.mytopbillboard.repositories.PlaylistRepository;
import com.mytopbillboard.services.Utils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PlaylistController {

    private final PlaylistRepository playlistDao;

    public PlaylistController(PlaylistRepository playlistDao) {
        this.playlistDao = playlistDao;
    }

    //    @PostMapping("/profile/{username}")
//    public String addSong(){
//        return "redirect:/siteViews/profile"; // provisional solution. Trying to explicitly not reload/redirect so that people will be able to add songs to playlist without reloading page.
//    }

    @PostMapping("/profile/{username}")
    public String addPlaylist(@ModelAttribute Playlist playlist){
        playlist.setUserId(Utils.currentUserProfile());
        playlistDao.save(playlist);
        return "siteViews/profile";
    }

    @PostMapping("/home")
    public String addSonHome(){
        return "siteViews/homepage"; // provisional solution. Trying to explicitly not reload/redirect so that people will be able to add songs to playlist without reloading page.
    }



}
