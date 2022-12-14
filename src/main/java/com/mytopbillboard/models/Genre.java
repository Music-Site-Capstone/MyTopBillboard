package com.mytopbillboard.models;

import javax.persistence.*;
import java.util.List;

@Entity(name = "Genre")
public class Genre {

    public Genre(long id, String name, List<Song> songs) {
        this.id = id;
        this.name = name;
        this.songs = songs;
    }

    public Genre(long id, String name) {
        this.name = name;
        this.id = id;
    }

    public Genre() {}

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

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "genre")
    private List<Song> songs;
        public List<Song> getSongs() {
            return songs;
        }
        public void setSongs(List<Song> songs) {
        this.songs = songs;
    }
}
