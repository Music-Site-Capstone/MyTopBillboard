package com.mytopbillboard.models;

public class Artist {

    public Artist(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Artist() {}

    private long id;
        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
    }

    private String name;
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
}
