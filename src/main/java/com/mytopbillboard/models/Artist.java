package com.mytopbillboard.models;

import javax.persistence.*;

import java.util.List;
@Entity(name = "Artists")
public class Artist {

    public Artist(long id, String name, List<Song> songs) {
        this.id = id;
        this.name = name;
        this.songs = songs;
    }

    public Artist(long id, String name) {
        this.id = id;
        this.name = name;
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
    private String name;
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "artist")
    private List<Song> songs;
        public List<Song> getSongs() {
            return songs;
        }
        public void setSongs(List<Song> songs) {
            this.songs = songs;
        }

}
