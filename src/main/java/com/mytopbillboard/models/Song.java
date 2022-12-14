package com.mytopbillboard.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "song")
public class Song {

    public Song(long id, String title, long artist, long genre, Artist artistObject, Genre genreObject, List<Playlist> playlists) {
        this.id = id;
        this.title = title;
        this.artist = artist;
        this.genre = genre;
        this.artistObject = artistObject;
        this.genreObject = genreObject;
        this.playlists = playlists;
    }

    public Song(long id, String title, long artist, long genre, Artist artistObject) {
        this.id = id;
        this.title = title;
        this.artist = artist;
        this.genre = genre;
        this.artistObject = artistObject;
    }

    public Song(long id, String title, long artist, long genre) {
        this.title = title;
        this.artist = artist;
        this.genre = genre;
        this.id = id;
    }

    public Song(String title, long artist, long genre) {
        this.title = title;
        this.artist = artist;
        this.genre = genre;
    }

    public Song(String title, long artist) {
        this.title = title;
        this.artist = artist;
    }

    public Song(String title) {
        this.title = title;
    }

    public Song() {}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
        public long getId() {
            return id;
        }
        public void setId(long id) {
            this.id = id;
        }

    @Column(nullable = false)
    private String title;
        public String getTitle() {
            return title;
        }
        public void setTitle(String title) {
        this.title = title;
    }

    @Column(nullable = false, insertable=false, updatable=false)
    private long artist;
        public long getArtist() {
            return artist;
        }
        public void setArtist(long artist) {
            this.artist = artist;
        }

    @Column(nullable = false, insertable=false, updatable=false)
    private long genre;
        public long getGenre() {
            return genre;
        }
        public void setGenre(long genre) {
            this.genre = genre;
        }

    @ManyToOne
    @JoinColumn(name = "artist_id")
    private Artist artistObject;
        public Artist getArtistObject() {
            return artistObject;
        }
        public void setArtistObject(Artist artistObject) {
            this.artistObject = artistObject;
        }

    @ManyToOne
    @JoinColumn(name = "genre_id")
    private Genre genreObject;
        public Genre getGenreObject() {
            return genreObject;
        }
        public void setGenreObject(Genre genreObject) {
        this.genreObject = genreObject;
    }


    @ManyToMany(mappedBy = "songs")
    private List<Playlist> playlists;
        public List<Playlist> getPlaylist() {
            return playlists;
        }
        public void setPlaylist(List<Playlist> playlists) {
            this.playlists = playlists;
        }
}
