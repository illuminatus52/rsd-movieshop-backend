package com.rsd_movieshop.controller;

import com.rsd_movieshop.model.Movie;
import com.rsd_movieshop.service.MovieService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping(path = "/api/movies")
public class MovieController {

    private final MovieService movieService;


    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }


    @GetMapping
    public List<Movie> getMovies() {
        System.out.println("MovieController: getMovies");
        return movieService.findMovies();
    }
    
    @GetMapping("/{movieID}")
    public Optional<Movie> getMovieDesc(@PathVariable int movieID) {
        System.out.println("MovieController: getMovieDescription");
        return movieService.findMovieById(movieID);
    }
    
    @PostMapping
    public void addNewMovie(@RequestBody Movie newMovie) {
        System.out.println("MovieController: addNewMovie");
        movieService.saveMovie(newMovie);
    }
    
    @PutMapping(path = "/{movieID}")
    public void updateMovie(@PathVariable int movieID) {
        // update specific movie
        System.out.println("MovieController: updateMovie");
    }
    
    
    @DeleteMapping("/{movieID}")
    public void deleteMovie(@PathVariable int movieID) {
        movieService.deleteMovieById(movieID);
        System.out.println("MovieController: deleteMovie");
    }
}
