package com.mytopbillboard.repositories;

import com.mytopbillboard.models.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RatingRepository extends JpaRepository<Rating, Long> {

    Rating findById(long id);

    Rating findByScore(short score);

}
