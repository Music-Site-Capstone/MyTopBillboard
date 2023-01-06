package com.mytopbillboard.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mytopbillboard.models.Playlist;
import com.mytopbillboard.models.Rating;
import com.mytopbillboard.models.Song;
import com.mytopbillboard.models.User;
import com.mytopbillboard.repositories.PlaylistRepository;
import com.mytopbillboard.repositories.RatingRepository;
import com.mytopbillboard.repositories.SongRepository;
import com.mytopbillboard.repositories.UserRepository;
import com.mytopbillboard.services.Utils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PlaylistController {
    private final RatingRepository ratingDao;
    private final UserRepository userDao;

    private final PlaylistRepository playlistDao;

    private final SongRepository songDao;

    public PlaylistController(RatingRepository ratingDao, UserRepository userDao, PlaylistRepository playlistDao, SongRepository songDao) {
        this.ratingDao = ratingDao;
        this.userDao = userDao;
        this.playlistDao = playlistDao;
        this.songDao = songDao;
    }


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

    @GetMapping("/profile/{username}/{plId}")
    public String getPlaylistSongs(Model model, @PathVariable("username") String username, @PathVariable("plId") Long plId, @RequestParam(name = "userId") long userId){
        System.out.println("get playlist songs works");
        List<Song> songs = songDao.findAll();
        model.addAttribute("songs", songs);
        model.addAttribute("singlePlaylistId", playlistDao.findById(plId));

        return "redirect:/profile/" + username;
    }




    @PostMapping("/home")
    public String addSonHome(){
        return "siteViews/homepage"; // provisional solution. Trying to explicitly not reload/redirect so that people will be able to add songs to playlist without reloading page.
    }

    @PostMapping("/song/playlist/{playlistId}/add/{songId}")
    public String addSongToPlaylist(@PathVariable long playlistId, @PathVariable long songId){
        Playlist playlist = playlistDao.findById(playlistId);
        List<Song> songs = playlist.getSong();
        //Get artist information<Name>
        //Get all the songs from artist
        //Check if the song title exists in the list of songs in mtb_db
        //If it does exist, get ID. Then add song, set songs to playlist, then save playlist
        //If the song DOESNT exist than DO the following:
        //Pull the genres from the artist, AND add genres that dont exist already (from the artist)
        //Save the new genres
        //Save the song with the genres associated with it
        songs.add(songDao.findById(songId));
        playlist.setSong(songs);
        playlistDao.save(playlist);
        return "redirect:/profile";
    }

    @PostMapping("/song/playlist/{playlistId}")
    public @ResponseBody void addSongToDB(@PathVariable long playlistId, @RequestBody Song song) throws JsonProcessingException {
        //Object Mapper
        //save song with songDao;
        System.out.println("inside addSongToDB");
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writeValueAsString(song));
    }


    @PostMapping("/rating/{owner}")
    public String rate(@ModelAttribute Rating rating, @RequestParam(name="playlistId") long playListId, @PathVariable("owner")String owner){
        User user = userDao.findById(Utils.currentUserProfile());
        Playlist playlist = playlistDao.findById(playListId);
        rating.setUser(user);
        rating.setPlaylist(playlist);
        ratingDao.save(rating);
        return "redirect:/profile/" + owner;

    }

    @GetMapping("profile/playlist/{plId}/{username}")
    @ResponseBody
    public Playlist displayPlaylistSongs(@PathVariable("plId") Long plId,@PathVariable("username") String username, Model model){
        System.out.println("the string inside display playlist songs");
        Playlist playlist = playlistDao.findById(plId).get();
//        model.addAttribute("displaySinglePlaylist", playlist);
        System.out.println(username);
        return playlist;
    }

}
