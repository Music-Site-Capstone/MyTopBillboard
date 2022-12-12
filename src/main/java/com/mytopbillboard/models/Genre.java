package com.mytopbillboard.models;

public class Genre {
    public Genre(long id, String name) {
        this.name = name;
        this.id = id;
    }

    public Genre() {}

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
