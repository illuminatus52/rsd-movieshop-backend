package com.rsd_movieshop.controller;

import com.rsd_movieshop.model.Genre;
import com.rsd_movieshop.service.GenreService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/genre")
public class GenreController {

	private final GenreService genreService;

	public GenreController(GenreService genreService) {
		this.genreService = genreService;
	}

	@GetMapping("/{genreName}")
	public ResponseEntity<Genre> getGenre(@PathVariable String genreName) {
		return genreService.findGenreByName(genreName);
	}

	@GetMapping
	public ResponseEntity<List<Genre>> getGenres() {
		return genreService.findAllGenre();
	}

	@PostMapping
	public ResponseEntity<Genre> addNewGenre(@RequestBody String genreName) {
		return genreService.saveGenre(genreName);
	}

	@PutMapping(path = "/{genreID}")
	public ResponseEntity<Genre> updateGenre(@PathVariable long genreID, @RequestParam String genreName,
			@RequestParam String movies) {
		return genreService.updateGenre(genreID, genreName, movies);
	}

	@DeleteMapping(path = "/{genreID}")
	public ResponseEntity<String> deleteGenre(@PathVariable int genreID) {
		return genreService.deleteGenre(genreID);
	}
}
