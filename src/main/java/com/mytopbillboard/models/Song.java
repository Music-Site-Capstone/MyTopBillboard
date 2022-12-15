package com.mytopbillboard.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "song")
public class Song {

    public Song(long id, String title, long artistId, long genreId, Artist artistObject, Genre genreObject, List<Playlist> playlists) {
        this.id = id;
        this.title = title;
        this.artistId = artistId;
        this.genreId = genreId;
        this.artistObject = artistObject;
        this.genreObject = genreObject;
        this.playlists = playlists;
    }

    public Song(long id, String title, long artistId, long genreId, Artist artistObject) {
        this.id = id;
        this.title = title;
        this.artistId = artistId;
        this.genreId = genreId;
        this.artistObject = artistObject;
    }

    public Song(long id, String title, long artistId, long genreId) {
        this.title = title;
        this.artistId = artistId;
        this.genreId = genreId;
        this.id = id;
    }

    public Song(String title, long artistId, long genreId) {
        this.title = title;
        this.artistId = artistId;
        this.genreId = genreId;
    }

    public Song(String title, long artistId) {
        this.title = title;
        this.artistId = artistId;
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
    private long artistId;
        public long getArtistId() {
            return artistId;
        }
        public void setArtistId(long artistId) {
            this.artistId = artistId;
        }

    @Column(nullable = false, insertable=false, updatable=false)
    private long genreId;
        public long getGenreId() {
            return genreId;
        }
        public void setGenreId(long genreId) {
            this.genreId = genreId;
        }

    @ManyToOne
    @JoinColumn(name = "artist")
    private Artist artistObject;
        public Artist getArtistObject() {
            return artistObject;
        }
        public void setArtistObject(Artist artistObject) {
            this.artistObject = artistObject;
        }

    @ManyToOne
    @JoinColumn(name = "genre")
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
