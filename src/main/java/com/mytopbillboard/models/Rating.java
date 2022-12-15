package com.mytopbillboard.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "rating")
public class Rating {

    public Rating(long id, long playlistId, short score, long userId, Playlist playlistObject, User userObject) {
        this.id = id;
        this.playlistId = playlistId;
        this.score = score;
        this.userId = userId;
        this.playlistObject = playlistObject;
        this.userObject = userObject;
    }

    public Rating(long id, long playlistId, short score, long userId) {
        this.id = id;
        this.playlistId = playlistId;
        this.score = score;
        this.userId = userId;
    }

    public Rating(long id, long playlistId, short score) {
        this.id = id;
        this.playlistId = playlistId;
        this.score = score;
    }

    public Rating(long playlistId, short score) {
        this.playlistId = playlistId;
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
    private long playlistId;
        public long getPlaylistId() {
            return playlistId;
        }

        public void setPlaylistId(long playlistId) {
            this.playlistId = playlistId;
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
    private long userId;
        public long getUserId() {
            return userId;
        }

        public void setUserId(long userId) {
            this.userId = userId;
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
