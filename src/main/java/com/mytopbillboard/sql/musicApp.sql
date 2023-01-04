DROP DATABASE IF EXISTS mtb_db;
CREATE DATABASE IF NOT EXISTS mtb_db;
GRANT ALL ON mtb_db.* TO 'codeup_test_user'@'localhost';
# USE mtb_db;
# DROP TABLE IF EXISTS user;
# CREATE TABLE user (id BIGINT NOT NULL AUTO_INCREMENT,
#                      username VARCHAR(50) NOT NULL,
#                      email VARCHAR(100) NOT NULL,
#                      password VARCHAR(255) NOT NULL,
#                      PRIMARY KEY (id),
#                      UNIQUE (username, email)
# );
# DROP TABLE IF EXISTS playlist;
# CREATE TABLE playlist (id BIGINT NOT NULL AUTO_INCREMENT,
#                    userId BIGINT NOT NULL,
#                    playlistName VARCHAR(50) NOT NULL,
#                    PRIMARY KEY (id)
# );
# DROP TABLE IF EXISTS rating;
# CREATE TABLE rating (id BIGINT NOT NULL AUTO_INCREMENT,
#                        userId BIGINT NOT NULL,
#                        playlistId BIGINT NOT NULL,
#                        score INT NOT NULL,
#                        PRIMARY KEY (id)
# );
# DROP TABLE IF EXISTS song;
# CREATE TABLE song (id BIGINT NOT NULL AUTO_INCREMENT,
#                      artistId BIGINT NOT NULL,
#                      genreId BIGINT NOT NULL,
#                      title VARCHAR(255),
#                      PRIMARY KEY (id)
# );
# DROP TABLE IF EXISTS artist;
# CREATE TABLE artist (id BIGINT NOT NULL AUTO_INCREMENT,
#                    artistName VARCHAR(75) NOT NULL UNIQUE,
#                    PRIMARY KEY (id)
# );
# DROP TABLE IF EXISTS genre;
# CREATE TABLE genre (id BIGINT NOT NULL AUTO_INCREMENT,
#                      genreName VARCHAR(75) NOT NULL UNIQUE,
#                      PRIMARY KEY (id)
# );