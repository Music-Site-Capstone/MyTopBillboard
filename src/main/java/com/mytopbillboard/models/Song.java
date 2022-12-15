package com.mytopbillboard.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "song")
public class Song {

    public Song(long id, String title, long artist_id, long genre_id, Artist artist, Genre genre, List<Playlist> playlists) {
        this.id = id;
        this.title = title;
        this.artist_id = artist_id;
        this.genre_id = genre_id;
        this.artist = artist;
        this.genre = genre;
        this.playlists = playlists;
    }

    public Song(long id, String title, long artist_id, long genre_id, Artist artist) {
        this.id = id;
        this.title = title;
        this.artist_id = artist_id;
        this.genre_id = genre_id;
        this.artist = artist;
    }

    public Song(long id, String title, long artist_id, long genre_id) {
        this.title = title;
        this.artist_id = artist_id;
        this.genre_id = genre_id;
        this.id = id;
    }

    public Song(String title, long artist_id, long genre_id) {
        this.title = title;
        this.artist_id = artist_id;
        this.genre_id = genre_id;
    }

    public Song(String title, long artist_id) {
        this.title = title;
        this.artist_id = artist_id;
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
    private long artist_id;
        public long getArtistId() {
            return artist_id;
        }
        public void setArtistId(long artist_id) {
            this.artist_id = artist_id;
        }

    @Column(nullable = false, insertable=false, updatable=false)
    private long genre_id;
        public long getGenreId() {
            return genre_id;
        }
        public void setGenreId(long genre_id) {
            this.genre_id = genre_id;
        }

    @ManyToOne
    private Artist artist;
        public Artist getArtist() {
            return artist;
        }
        public void setArtist(Artist artist) {
            this.artist = artist;
        }

    @ManyToOne
    private Genre genre;
        public Genre getGenre() {
            return genre;
        }
        public void setGenre(Genre genre) {
        this.genre = genre;
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
