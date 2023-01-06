package com.mytopbillboard.repositories;

import com.mytopbillboard.models.Artist;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ArtistRepository extends JpaRepository<Artist, Long> {

    Artist findById(long id);

    Artist findByArtistName(String artistName);

}