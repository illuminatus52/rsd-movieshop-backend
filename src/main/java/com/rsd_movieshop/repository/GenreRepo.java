package com.rsd_movieshop.repository;

import com.rsd_movieshop.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepo extends JpaRepository<Genre, Long> {

    Genre findByGenreId(long id);

    Genre findGenreByName(String genreName);
}
