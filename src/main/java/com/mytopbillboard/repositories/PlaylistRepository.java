package com.mytopbillboard.repositories;

import com.mytopbillboard.models.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaylistRepository extends JpaRepository<Playlist, Long> {
    Playlist findById(long id);
    Playlist findByUser_Id(long user_id);

    Playlist findByPlaylistName(String playListName);

}