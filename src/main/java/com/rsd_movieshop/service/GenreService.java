package com.rsd_movieshop.service;

import com.rsd_movieshop.repositorie.GenreRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GenreService {

    private final GenreRepo genreRepo;


    public GenreService(GenreRepo genreRepo) {
        this.genreRepo = genreRepo;
    }
}
