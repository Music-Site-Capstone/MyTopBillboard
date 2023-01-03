package com.mytopbillboard.controllers;

import com.mytopbillboard.models.Playlist;
import com.mytopbillboard.repositories.PlaylistRepository;
import com.mytopbillboard.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PlaylistController {
private final UserRepository userDao;
    private final PlaylistRepository playlistDao;

    public PlaylistController(UserRepository userDao, PlaylistRepository playlistDao) {
        this.userDao = userDao;
        this.playlistDao = playlistDao;
    }

    //    @PostMapping("/profile/{username}")
//    public String addSong(){
//        return "redirect:/siteViews/profile"; // provisional solution. Trying to explicitly not reload/redirect so that people will be able to add songs to playlist without reloading page.
//    }

//    @PostMapping("/profile/{username}")
//    public HttpServletResponse addPlaylist(@ModelAttribute Playlist playlist, HttpServletResponse response){
//        playlist.setUserId(Utils.currentUserProfile());
//        playlistDao.save(playlist);
//        return response; //return response object with a set status method
//    }

    @PostMapping("/profile/{username}")
    public String addPlaylist(@PathVariable("username") String username, @RequestParam(name = "playlistName") String playlistName, @RequestParam(name = "userId") long userId){
        System.out.println("I AM FUNCTIONAL AT ALL");
        System.out.println(userId);
        Playlist playlist = new Playlist();
        playlist.setPlaylistName(playlistName);
        playlist.setUser(userDao.findById(userId));
        playlistDao.save(playlist);
        return "redirect:/profile/" + username; //return response object with a set status method
    }

    @PostMapping("profile/{username}/{playlistName}")
    public String showSongsInPlaylist(Model model, @PathVariable("username") String username, @RequestParam(name= "playlistName") String playlistName)

//    public String create(
//            @RequestParam(name = "title") String title,
//            @RequestParam(name = "description") String description
//    ) {
//        Ad ad = new Ad();
//        ad.setTitle(title);
//        ad.setDescription(description);
//        // save the ad...
//    }




    @PostMapping("/home")
    public String addSonHome(){
        return "siteViews/homepage"; // provisional solution. Trying to explicitly not reload/redirect so that people will be able to add songs to playlist without reloading page.
    }




}
