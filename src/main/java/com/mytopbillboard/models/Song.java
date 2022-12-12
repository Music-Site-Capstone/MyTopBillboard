package com.mytopbillboard.models;

public class Song {

    private String title;

    private long artist_id;

    private long genre_id;

    private long song_id;

    public Song() {
    }

    public Song(String title) {
        this.title = title;
    }

    public Song(String title, long artist_id) {
        this.title = title;
        this.artist_id = artist_id;
    }

    public Song(String title, long artist_id, long genre_id) {
        this.title = title;
        this.artist_id = artist_id;
        this.genre_id = genre_id;
    }

    public Song(String title, long artist_id, long genre_id, long song_id) {
        this.title = title;
        this.artist_id = artist_id;
        this.genre_id = genre_id;
        this.song_id = song_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getArtist_id() {
        return artist_id;
    }

    public void setArtist_id(long artist_id) {
        this.artist_id = artist_id;
    }

    public long getGenre_id() {
        return genre_id;
    }

    public void setGenre_id(long genre_id) {
        this.genre_id = genre_id;
    }

    public long getSong_id() {
        return song_id;
    }

    public void setSong_id(long song_id) {
        this.song_id = song_id;
    }

}
