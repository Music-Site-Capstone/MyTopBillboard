DROP DATABASE IF EXISTS mtb_db;
CREATE DATABASE IF NOT EXISTS mtb_db;
GRANT ALL ON mtb_db.* TO 'codeup_test_user'@'localhost';
USE mtb_db;
DROP TABLE IF EXISTS user;
CREATE TABLE user (id INT NOT NULL AUTO_INCREMENT,
                     username VARCHAR(50) NOT NULL,
                     email VARCHAR(100) NOT NULL,
                     password VARCHAR(255),
                     PRIMARY KEY (id),
                     UNIQUE (username, email)
);
DROP TABLE IF EXISTS playlist;
CREATE TABLE playlist (id INT NOT NULL AUTO_INCREMENT,
                   userId INT NOT NULL,
                   playlistName VARCHAR(50),
                   PRIMARY KEY (id)
);
DROP TABLE IF EXISTS rating;
CREATE TABLE rating (id INT NOT NULL AUTO_INCREMENT,
                       userId INT NOT NULL,
                       playlistId INT NOT NULL,
                       score INT,
                       PRIMARY KEY (id)
);
DROP TABLE IF EXISTS song;
CREATE TABLE song (id INT NOT NULL AUTO_INCREMENT,
                     artistId INT NOT NULL,
                     genreId INT NOT NULL,
                     title VARCHAR(255),
                     PRIMARY KEY (id)
);
DROP TABLE IF EXISTS artist;
CREATE TABLE artist (id INT NOT NULL AUTO_INCREMENT,
                   artistName VARCHAR(75),
                   PRIMARY KEY (id)
);
DROP TABLE IF EXISTS genre;
CREATE TABLE genre (id INT NOT NULL AUTO_INCREMENT,
                     genreName VARCHAR(75),
                     PRIMARY KEY (id)
);