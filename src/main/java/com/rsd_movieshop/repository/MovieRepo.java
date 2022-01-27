package com.rsd_movieshop.repository;

import com.rsd_movieshop.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepo extends JpaRepository<Movie,Long> {
    Movie findByMovieId(long id);
    Movie findMovieByTitle(String title);
}
