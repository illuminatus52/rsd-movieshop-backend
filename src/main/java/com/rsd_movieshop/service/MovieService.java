package com.rsd_movieshop.service;

import com.rsd_movieshop.repository.MovieRepo;
import org.springframework.stereotype.Service;

@Service
public class MovieService {

    private final MovieRepo movieRepo;


    public MovieService(MovieRepo movieRepo) {
        this.movieRepo = movieRepo;
    }
}
