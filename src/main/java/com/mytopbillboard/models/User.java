package com.mytopbillboard.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "user")
public class User {

    public User(long id, String username, String email, String password, List<Playlist> playlists, List<Rating> ratings) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.playlists = playlists;
        this.ratings = ratings;
    }

    public User(long id, String username, String email, String password) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }


    public User(){}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
        public long getId() {
                        return id;
                    }

        public void setId(long id) {
            this.id = id;
        }

    @Column(nullable = false, unique = true, length = 50)
    private String username;
        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

    @Column(nullable = false, unique = true, length = 100)
    private String email;
        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

    @Column(nullable = false, length = 50)
    private String password;
        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Playlist> playlists;
        public List<Playlist> getPlaylist() {
            return playlists;
        }

        public void setPlaylist(List<Playlist> playlists) {
            this.playlists = playlists;
        }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userObject")
    private List<Rating> ratings;
        public List<Rating> getRatings() {
            return ratings;
        }
        public void setRatings(List<Rating> ratings) {
            this.ratings = ratings;
        }
}
