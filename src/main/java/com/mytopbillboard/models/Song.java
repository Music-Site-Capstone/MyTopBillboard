package com.mytopbillboard.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "song")
public class Song {

    public Song(long id, String title, Artist artist, List<Playlist> playlists, String image) {
        this.id = id;
        this.title = title;
        this.artist = artist;
        this.playlists = playlists;
        this.image = image;
    }

    public Song(long id, String title, Artist artist, String image){
        this.id = id;
        this.artist = artist;
        this.title = title;
        this.image = image;
    }

    public Song(long id, String title, Artist artist) {
        this.id = id;
        this.title = title;
        this.artist = artist;
    }

    public Song(long id, String title) {
        this.title = title;
        this.id = id;
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

    @Column(nullable = false)
    private String image;

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

    @Column(nullable = false)
    private String previewUrl;

    public String getPreviewUrl() {
        return previewUrl;
    }

    public void setPreviewUrl(String previewUrl) {
        this.previewUrl = previewUrl;
    }

    @ManyToOne
    private Artist artist;
        public Artist getArtist() {
            return artist;
        }
        public void setArtist(Artist artist) {
            this.artist = artist;
        }



    @ManyToMany(mappedBy = "songs")
    private List<Playlist> playlists;
        @JsonIgnore
        public List<Playlist> getPlaylist() {
            return playlists;
        }
        public void setPlaylist(List<Playlist> playlists) {
            this.playlists = playlists;
        }
}
