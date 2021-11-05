package com.rsd_movieshop.service;

import com.rsd_movieshop.model.Movie;
import com.rsd_movieshop.repository.MovieRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    private final MovieRepo movieRepo;


    public MovieService(MovieRepo movieRepo) {
        this.movieRepo = movieRepo;
    }

    public void saveMovie(Movie movie) {
    movieRepo.save(movie);
    }

    public Optional<Movie> findMovieById(long id){
        return movieRepo.findById(id);
    }

    public List<Movie> findMovies(){
        return movieRepo.findAll();
    }

    public void deleteMovieById(long id){
        movieRepo.deleteById(id);
    }
}