package com.mytopbillboard.models;

public class Rating {
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


    private long id;
        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

    private long playlist_id;
        public long getPlaylist_id() {
            return playlist_id;
        }

        public void setPlaylist_id(long playlist_id) {
            this.playlist_id = playlist_id;
        }

    private short score;
        public short getScore() {
            return score;
        }

        public void setScore(short score) {
            this.score = score;
        }

    private long user_id;
        public long getUser_id() {
            return user_id;
        }

        public void setUser_id(long user_id) {
            this.user_id = user_id;
        }
}
