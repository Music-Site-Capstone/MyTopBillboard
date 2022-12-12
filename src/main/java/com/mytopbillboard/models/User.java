package com.mytopbillboard.models;

public class User {
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


    private long id;
        public long getId() {
                    return id;
                }

        public void setId(long id) {
            this.id = id;
        }

    private String username;
        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

    private String email;
        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

    private String password;
        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
}
