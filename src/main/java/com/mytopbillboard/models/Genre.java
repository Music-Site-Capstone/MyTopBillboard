package com.mytopbillboard.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table (name = "genre")
public class Genre {

    public Genre(long id, String genreName, List<Song> songs) {
        this.id = id;
        this.genreName = genreName;
        this.songs = songs;
    }

    public Genre(long id, String genreName) {
        this.genreName = genreName;
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
    private String genreName;
        public String getGenreName() {
            return genreName;
        }
        public void setGenreName(String genreName) {
        this.genreName = genreName;
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
