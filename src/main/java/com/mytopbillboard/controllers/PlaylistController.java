package com.mytopbillboard.controllers;

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


    @PostMapping("/home")
    public String addSonHome(){
        return "siteViews/homepage"; // provisional solution. Trying to explicitly not reload/redirect so that people will be able to add songs to playlist without reloading page.
    }

    @PostMapping("/song/playlist/{playlistId}/add/{songId}")
    public String addSongToPlaylist(@PathVariable long playlistId, @PathVariable long songId, @RequestBody Song song){
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

    @PostMapping("/rating/{owner}")
    public String rate(@ModelAttribute Rating rating, @RequestParam(name="playlistId") long playListId, @PathVariable("owner")String owner){
        User user = userDao.findById(Utils.currentUserProfile());
        Playlist playlist = playlistDao.findById(playListId);
        rating.setUser(user);
        rating.setPlaylist(playlist);
        ratingDao.save(rating);
        return "redirect:/profile/" + owner;

    }



}
