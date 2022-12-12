package com.mytopbillboard.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtistRepository extends JpaRepository<Artist, Long> {

    Artist findById(long id);

    Artist findByName(String name);

}
