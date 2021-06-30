package com.rsd_movieshop.controller;

import com.rsd_movieshop.model.Movie;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;


@RestController
@RequestMapping("/movies")
public class MovieController {
    
    @GetMapping
    public ArrayList<Movie> getMovies() {
        // return movie catalogue
        System.out.println("MovieController: getMovies");
        return null;
    }
    
    @GetMapping("/{movieID}")
    public Movie getMovieDesc(@PathVariable int movieID) {
        // return movie description
        System.out.println("MovieController: getMovieDescription");
        return null;
    }
    
    @PostMapping
    public void addNewMovie(@RequestBody Movie newMovie) {
        // add a new movie
        System.out.println("MovieController: addNewMovie");
    }
    
    @PutMapping(path = "/{movieID}")
    public void updateMovie(@PathVariable int movieID) {
        // update specific movie
        System.out.println("MovieController: updateMovie");
    }
    
    
    @DeleteMapping("/{movieID}")
    public void deleteMovie(@PathVariable int movieID) {
        // delete a movie
        System.out.println("MovieController: deleteMovie");
    }
}