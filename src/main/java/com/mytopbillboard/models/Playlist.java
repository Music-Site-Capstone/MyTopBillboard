package com.mytopbillboard.models;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "playlist")
public class Playlist {

    public Playlist(long id, long userId, String playlistName, User userObject, List<Rating> rating, List<Song> songs) {
        this.id = id;
        this.userId = userId;
        this.playlistName = playlistName;
        this.userObject = userObject;
        this.rating = rating;
        this.songs = songs;
    }

    public Playlist(long id, long userId, String playlistName, User userObject, List<Rating> rating) {
        this.id = id;
        this.userId = userId;
        this.playlistName = playlistName;
        this.userObject = userObject;
        this.rating = rating;
    }

    public Playlist(long id, long userId, String playlistName) {
        this.id = id;
        this.userId = userId;
        this.playlistName = playlistName;
    }

    public Playlist(long id, String playlistName) {
        this.id = id;
        this.playlistName = playlistName;
    }

    public Playlist(){}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
        public long getId() {
            return id;
        }
        public void setId(long id) {
            this.id = id;
        }

    @Column(nullable = false, insertable=false, updatable=false)
    private long userId;
        public long getUserId() {
            return userId;
        }
        public void setUserId(long userId) {
            this.userId = userId;
        }

    @Column(nullable = false, length = 50)
    private String playlistName;
        public String getPlaylistName() {
            return playlistName;
        }
        public void setPlaylistName(String playlistName) {
            this.playlistName = playlistName;
        }

    @ManyToOne
    private User userObject;
        public User getUserObject() {
            return userObject;
        }
        public void setUserObject(User userObject) {
            this.userObject = userObject;
        }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "playlist")
    private List<Rating> rating;
        public List<Rating> getRating() {
            return rating;
        }
        public void setRating(List<Rating> rating) {
            this.rating = rating;
        }



    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "playlist_song",
            joinColumns = {@JoinColumn(name="playlist_id")},
            inverseJoinColumns = {@JoinColumn(name="song_id")}
    )
    private List<Song> songs;
        public List<Song> getSong() {
            return songs;
        }

        public void setSong(List<Song> songs) {
            this.songs = songs;
        }
}
