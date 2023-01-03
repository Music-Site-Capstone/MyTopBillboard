package com.mytopbillboard.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "artist")
public class Artist {

    public Artist(long id, String artistName, List<Song> songs) {
        this.id = id;
        this.artistName = artistName;
        this.songs = songs;
    }

    public Artist(long id, String artistName) {
        this.id = id;
        this.artistName = artistName;
    }

    public Artist() {}
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
        public long getId() {
            return id;
        }
        public void setId(long id) {
            this.id = id;
    }

    @Column(unique = true, length = 75, nullable = false)
    private String artistName;
        public String getArtistName() {
            return artistName;
        }
        public void setArtistName(String artistName) {
            this.artistName = artistName;
        }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "artist")
    private List<Song> songs;
        public List<Song> getSongs() {
            return songs;
        }
        public void setSongs(List<Song> songs) {
            this.songs = songs;
        }

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name="artist_genres",
            joinColumns = @JoinColumn(name= "artist_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    private List<Genre> genres;

        public List<Genre> getGenres() {
            return genres;
        }

        public void setGenres(List<Genre> genres) {
            this.genres = genres;
        }
}
