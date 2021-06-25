package com.rsd_movieshop.controller;

import com.rsd_movieshop.model.Movie;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;


@RestController
@RequestMapping("/movies")
public class MovieController {
    
    private Movie movie;
    
    @GetMapping
    public ArrayList<Movie> getMovies() {
        // return movie catalogue
    }
    
    @GetMapping("/{movieID}")
    public Movie getMovieDesc(@PathVariable int movieID) {
        // return movie description
    }
    
    @PostMapping
    public void addNewMovie(Movie newMovie) {
        // add a new movie
    }
    
    @PutMapping(path = "/{movieID}")
    public void updateMovie(@PathVariable int movieID) {
        // update specific movie
    }
    
    
    @DeleteMapping("/{movieID}")
    public void deleteMovie(@PathVariable int movieID) {
        // delete a movie
    }
}
