package com.mytopbillboard.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "rating")
public class Rating {

    public Rating(long id, long playlist_id, short score, long user_id, Playlist playlist, User user) {
        this.id = id;
        this.playlist_id = playlist_id;
        this.score = score;
        this.user_id = user_id;
        this.playlist = playlist;
        this.user = user;
    }

    public Rating(long id, long playlist_id, short score, long user_id) {
        this.id = id;
        this.playlist_id = playlist_id;
        this.score = score;
        this.user_id = user_id;
    }

    public Rating(long id, long playlist_id, short score) {
        this.id = id;
        this.playlist_id = playlist_id;
        this.score = score;
    }

    public Rating(long playlist_id, short score) {
        this.playlist_id = playlist_id;
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
    private long playlist_id;

        public long getPlaylistId() {
            return playlist_id;
        }
        public void setPlaylistId(long playlist_id) {
            this.playlist_id = playlist_id;
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
    private long user_id;
        public long getUserId() {
            return user_id;
        }
        public void setUserId(long user_id) {
            this.user_id = user_id;
        }

    @ManyToOne
    private Playlist playlist;
        @JsonIgnore
        public Playlist getPlaylist() {
            return playlist;
        }
        public void setPlaylist(Playlist playlist) {
            this.playlist = playlist;
        }

    @ManyToOne
    private User user;
        @JsonIgnore
        public User getUser() {
            return user;
        }
        public void setUser(User user) {
            this.user = user;
        }
}
