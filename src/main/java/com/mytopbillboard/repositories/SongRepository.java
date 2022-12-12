package com.mytopbillboard.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SongRepository<Song> extends JpaRepository<Song, Long> {

    Song findById(long id);

}
