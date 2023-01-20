package com.mytopbillboard.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mytopbillboard.models.*;
import com.mytopbillboard.repositories.*;
import com.mytopbillboard.services.Utils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@Controller
public class PlaylistController {
    private final RatingRepository ratingDao;
    private final UserRepository userDao;
    private final PlaylistRepository playlistDao;
    private final SongRepository songDao;
    private final ArtistRepository artistDao;
    private final GenreRepository genreDao;

    public PlaylistController(RatingRepository ratingDao, UserRepository userDao, PlaylistRepository playlistDao, SongRepository songDao, ArtistRepository artistDao, GenreRepository genreDao) {
        this.ratingDao = ratingDao;
        this.userDao = userDao;
        this.playlistDao = playlistDao;
        this.songDao = songDao;
        this.artistDao = artistDao;
        this.genreDao = genreDao;
    }

    //used for saving playlists
    @PostMapping("/profile/{username}")
    public String addPlaylist(@PathVariable("username") String username, @RequestParam(name = "playlistName") String playlistName, @RequestParam(name = "userId") long userId){
        Playlist playlist = new Playlist();
        playlist.setPlaylistName(playlistName);
        playlist.setUser(userDao.findById(userId));
        playlistDao.save(playlist);
        return "redirect:/profile/" + username; // (idea for not redirecting) return response object with a set status method
    }

    @GetMapping("/profile/{username}/{plId}")
    public String getPlaylistSongs(Model model, @PathVariable("username") String username, @PathVariable("plId") Long plId, @RequestParam(name = "userId") long userId){
        System.out.println("get playlist songs works");
        List<Song> songs = songDao.findAll();
        model.addAttribute("songs", songs);
        model.addAttribute("singlePlaylistId", playlistDao.findById(plId));

        return "redirect:/profile/" + username;
    }

    @PostMapping("/profile/playlist/delete/{username}")
    public String deletePlaylist(@PathVariable("username") String username, @RequestParam(name = "playlistId") long playlistId, @RequestParam(name = "userId") long userId){
        Playlist playlistToDelete = playlistDao.findById(playlistId);
        playlistToDelete.getSong().clear();
        playlistDao.delete(playlistToDelete);
        return "redirect:/profile/" + username; //return response object with a set status method
    }

    @PostMapping("/profile/playlist/{songId}/delete/{username}")
    public @ResponseBody void deletePlaylistSong(@PathVariable("username") String username, @PathVariable("songId") long songId, @RequestBody Playlist playlist) throws JsonProcessingException{
        Song songToDelete = songDao.findById(songId);
        Playlist playlistToUpdate = playlistDao.findById(playlist.getId());
        playlistToUpdate.getSong().remove(songToDelete);
        playlistDao.save(playlistToUpdate);
//        return "redirect:/profile/" + username; //return response object with a set status method
    }


    @PostMapping("/home")
    public String addSonHome(){
        return "siteViews/homepage"; // provisional solution. Trying to explicitly not reload/redirect so that people will be able to add songs to playlist without reloading page.
    }

    @PostMapping("/song/playlist/{playlistId}/add/{songId}")
    public String addSongToPlaylist(@PathVariable long playlistId, @PathVariable long songId, @RequestParam(name = "songTitle")String songTitle){
        Playlist playlist = playlistDao.findById(playlistId);
        List<Song> songs = playlist.getSong();
        List<Song> allSongs = songDao.findAll();

        //If the song exists in the DB, then we add the song to the playlist from the database instead.
        if (allSongs.contains(songDao.findByTitle(songTitle))){

        }

        songs.add(songDao.findById(songId));
        playlist.setSong(songs);
        playlistDao.save(playlist);
        return "redirect:/profile";
    }


    // method for adding songs to database and playlist on button click.
    @PostMapping("/song/playlist/{playlistId}")
    public @ResponseBody void addSongToDB(@PathVariable long playlistId, @RequestBody Song song) throws JsonProcessingException {
        //Object Mapper
        //save song with songDao;
        System.out.println("inside addSongToDB");
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writeValueAsString(song));
        //finding artist by artist name but getting around duplicates
        Artist artistDB = artistDao.findByArtistName(song.getArtist().getArtistName());
        if (artistDB == null) {
            //if not there then add, add song to db, and add to playlist
            Artist artist = new Artist();
            artist.setArtistName(song.getArtist().getArtistName());
            artistDB = artistDao.save(artist);
            song.setArtist(artistDB);
            song = songDao.save(song);
            Playlist playlist = playlistDao.findById(playlistId);
            playlist.getSong().add(song);
            playlistDao.save(playlist);
        } else {
            // since artist exists in DB need to check if song exists for that artist
            List<String> songDB = new ArrayList<>();
            artistDB.getSongs().forEach(artistSong -> {
                songDB.add(artistSong.getTitle());
            });
            if (!songDB.contains(song.getTitle())) {
                // song does not exist for artist, so add song and add to playlist
                song.setArtist(artistDB);
                song = songDao.save(song);
                Playlist playlist = playlistDao.findById(playlistId);
                playlist.getSong().add(song); //it may be better to use findbytitleandartist like below
                playlistDao.save(playlist);
            } else {
                //song does exist, so add it to the playlist from the DB
                Playlist playlist = playlistDao.findById(playlistId);
                playlist.getSong().add(songDao.findByTitleAndArtist(song.getTitle(), artistDB));
                playlistDao.save(playlist);
            }
        }

    }

    //this method is used to save ratings
    @PostMapping("/rating/{owner}")
    public String rate(@ModelAttribute Rating rating, @RequestParam(name="playlistId") long playListId, @PathVariable("owner")String owner){
        User user = userDao.findById(Utils.currentUserProfile());
        Playlist playlist = playlistDao.findById(playListId);
        rating.setUser(user);
        rating.setPlaylist(playlist);
        ratingDao.save(rating);
        return "redirect:/profile/" + owner;

    }

    // returns playlist object to load individual songs onto page
    @GetMapping("profile/playlist/{plId}/{username}")
    @ResponseBody
    public Playlist displayPlaylistSongs(@PathVariable("plId") Long plId){
        Playlist playlist = playlistDao.findById(plId).get();
        System.out.println(playlist.getSong());
        return playlist;
    }

}
