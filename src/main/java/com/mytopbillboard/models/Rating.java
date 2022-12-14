package com.mytopbillboard.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "rating")
public class Rating {

    public Rating(long id, long playlist, short score, long user, Playlist playlistObject, User userObject) {
        this.id = id;
        this.playlist = playlist;
        this.score = score;
        this.user = user;
        this.playlistObject = playlistObject;
        this.userObject = userObject;
    }

    public Rating(long id, long playlist, short score, long user) {
        this.id = id;
        this.playlist = playlist;
        this.score = score;
        this.user = user;
    }

    public Rating(long id, long playlist, short score) {
        this.id = id;
        this.playlist = playlist;
        this.score = score;
    }

    public Rating(long playlist, short score) {
        this.playlist = playlist;
        this.score = score;
    }

    public Rating(){}


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
    private long playlist;
        public long getPlaylist() {
            return playlist;
        }

        public void setPlaylist(long playlist) {
            this.playlist = playlist;
        }

    @Column(nullable = false)
    private short score;
        public short getScore() {
            return score;
        }

        public void setScore(short score) {
            this.score = score;
        }

    @Column(nullable = false, insertable=false, updatable=false)
    private long user;
        public long getUser() {
            return user;
        }

        public void setUser(long user) {
            this.user = user;
        }

    @ManyToOne
    private Playlist playlistObject;
        public Playlist getPlaylistObject() {
            return playlistObject;
        }

        public void setPlaylistObject(Playlist playlistObject) {
            this.playlistObject = playlistObject;
        }

    @ManyToOne
    private User userObject;
        public User getUserObject() {
            return userObject;
        }
        public void setUserObject(User userObject) {
            this.userObject = userObject;
        }
}
