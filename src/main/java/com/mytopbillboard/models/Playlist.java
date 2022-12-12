package com.mytopbillboard.models;

public class Playlist {
    public Playlist(long id, long user_id, String playlistName) {
        this.id = id;
        this.user_id = user_id;
        this.playlistName = playlistName;
    }

    public Playlist(long id, String playlistName) {
        this.id = id;
        this.playlistName = playlistName;
    }

    public Playlist(){}

    private long id;
        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }


    private long user_id;
        public long getUser_id() {
            return user_id;
        }

        public void setUser_id(long user_id) {
            this.user_id = user_id;
        }

    private String playlistName;
        public String getPlaylistName() {
            return playlistName;
        }

        public void setPlaylistName(String playlistName) {
            this.playlistName = playlistName;
        }
}
