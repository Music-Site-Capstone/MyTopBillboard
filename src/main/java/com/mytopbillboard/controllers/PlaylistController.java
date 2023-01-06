package com.mytopbillboard.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mytopbillboard.models.*;
import com.mytopbillboard.repositories.*;
import com.mytopbillboard.services.Utils;
import org.springframework.security.core.parameters.P;
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

    private final ArtistRepository artistDao;
//need artist to save song
    private final GenreRepository genreDao;
    //needed genre to save song

    public PlaylistController(RatingRepository ratingDao, UserRepository userDao, PlaylistRepository playlistDao, SongRepository songDao, ArtistRepository artistDao, GenreRepository genreDao) {
        this.ratingDao = ratingDao;
        this.userDao = userDao;
        this.playlistDao = playlistDao;
        this.songDao = songDao;
        this.artistDao = artistDao;
        this.genreDao = genreDao;
        //added two more constructors
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
        //need to save genre but getting error resulting from duplicates
        for (Genre genre : song.getArtist().getGenres()) {
            Genre genreDB = new Genre();
            genreDB.setGenreName(genre.getGenreName());
//            genreDB =  genreDao.save(genreDB);
        }
        //finding artist by artist name but getting around duplicates
        Artist artistDB = artistDao.findByArtistName(song.getArtist().getArtistName());
        if (artistDB == null) {
            //if not there then add
            Artist artist = new Artist();
            artist.setArtistName(song.getArtist().getArtistName());
            artistDB = artistDao.save(artist);
        }
        //find playlist by id and then add song into playlist and then save
        song.setArtist(artistDB);
        song = songDao.save(song);
        Playlist playlist = playlistDao.findById(playlistId);
        playlist.getSong().add(song);
        playlistDao.save(playlist);
    }

    //for genre table put default value of 1 for all songs when saving songs


    @PostMapping("/rating/{owner}")
    public String rate(@ModelAttribute Rating rating, @RequestParam(name="playlistId") long playListId, @PathVariable("owner")String owner){
        User user = userDao.findById(Utils.currentUserProfile());
        Playlist playlist = playlistDao.findById(playListId);
        rating.setUser(user);
        rating.setPlaylist(playlist);
        ratingDao.save(rating);
        return "redirect:/profile/" + owner;

    }

    @GetMapping("profile/playlist/{plId}")
    public @ResponseBody Playlist displayPlaylistSongs(@PathVariable("plId") Long plId){
        System.out.println("the string inside display playlist songs");
        Playlist playlist = playlistDao.findById(plId).get();
        return playlist;



    }

}
