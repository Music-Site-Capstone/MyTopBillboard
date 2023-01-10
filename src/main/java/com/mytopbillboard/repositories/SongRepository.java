package com.mytopbillboard.repositories;

import com.mytopbillboard.models.Artist;
import com.mytopbillboard.models.Song;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SongRepository extends JpaRepository<Song, Long> {

    Song findById(long id);

    Song findByTitle(String title);

    Song findByTitleAndArtist(String title, Artist artist);


}