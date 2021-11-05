package com.rsd_movieshop.service;

import com.rsd_movieshop.model.Genre;
import com.rsd_movieshop.repository.GenreRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GenreService {

    private final GenreRepo genreRepo;


    public GenreService(GenreRepo genreRepo) {
        this.genreRepo = genreRepo;
    }

    public Optional<Genre> findGenreById(long id){
        return genreRepo.findById(id);
    }

    public List<Genre> findAllGenre(){
        return genreRepo.findAll();
    }

    public void saveGenre(Genre genre){
        genreRepo.save(genre);
    }

    public void deleteGenre(long id){
        genreRepo.deleteById(id);
    }
}
