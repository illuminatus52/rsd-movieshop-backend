package com.rsd_movieshop.controller;

import com.rsd_movieshop.model.Movie;
import com.rsd_movieshop.service.MovieService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/")
public class MovieController {

	private final MovieService movieService;

	public MovieController(MovieService movieService) {
		this.movieService = movieService;
	}

	@GetMapping(path = "movies")
	public ResponseEntity<List<Movie>> getMovies() {
		return movieService.findMovies();
	}

	@GetMapping("user/movies/{movieID}")
	public ResponseEntity<Movie> getMovieDesc(@PathVariable int movieID) {
		return movieService.findMovieById(movieID);
	}

	@PostMapping(path = "admin/movies/addMovie")
	public ResponseEntity<Movie> addNewMovie(@RequestBody Movie newMovie) {
		return movieService.saveMovie(newMovie);
	}

	@PutMapping(path = "admin/movies/{movieID}")
	public ResponseEntity<Movie> updateMovie(@PathVariable long movieID, @RequestParam int releaseYear,
			@RequestParam int movieStock, @RequestParam String title, @RequestParam String genres,
			@RequestParam String picture, @RequestParam double price) {
		return movieService.updateMovie(movieID, releaseYear, movieStock, title, genres, picture, price);
	}

	@DeleteMapping("admin/movies/{movieID}")
	public ResponseEntity<String> deleteMovie(@PathVariable int movieID) {
		return movieService.deleteMovieById(movieID);
	}
}