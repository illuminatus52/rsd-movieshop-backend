package com.rsd_movieshop.controller;

import com.rsd_movieshop.model.Movie;
import com.rsd_movieshop.model.MovieRequest;
import com.rsd_movieshop.responseModels.MovieResponse;
import com.rsd_movieshop.service.MovieService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/")
public class MovieController {
	
	private MovieService movieService;
	
	public MovieController(MovieService movieService) {
		this.movieService = movieService;
	}
	
	@GetMapping(path = "movies/all")
	public ResponseEntity<List<MovieResponse>> getMovies() {
		return movieService.findMovies();
	}
	
	@GetMapping(path = "movies/{movieID}")
	public ResponseEntity<MovieResponse> getMovieDesc(@PathVariable int movieID) {
		return movieService.findMovieById(movieID);
	}
	
	@PostMapping(path = "admin/addMovie")
	public ResponseEntity<MovieResponse> addNewMovie(@RequestBody Movie newMovie) {
		return movieService.saveNewMovie(newMovie);
	}
	
	@PutMapping(path = "admin/movies/{movieID}")
	public ResponseEntity<MovieResponse> updateMovie(
			@PathVariable long movieID,
			@RequestBody MovieRequest movie) {
		return movieService.updateMovie(movieID, movie);
	}
	
	@DeleteMapping(path = "admin/movies/{movieID}")
	public ResponseEntity<String> deleteMovie(@PathVariable int movieID) {
		return movieService.deleteMovieById(movieID);
	}
}