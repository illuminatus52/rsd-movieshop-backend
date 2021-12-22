package com.rsd_movieshop.controller;

import com.rsd_movieshop.model.Genre;
import com.rsd_movieshop.model.GenreResponse;
import com.rsd_movieshop.service.GenreService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/")
public class GenreController {

	private final GenreService genreService;

	public GenreController(GenreService genreService) {
		this.genreService = genreService;
	}

	@GetMapping(path = "genre/{genreName}")
	public ResponseEntity<GenreResponse> getGenre(@PathVariable String genreName) {
		return genreService.findGenreByName(genreName);
	}

	@GetMapping(path = "genre/all")
	public ResponseEntity<List<GenreResponse>> getGenres() {
		return genreService.findAllGenre();
	}

	@PostMapping(path = "admin/genre/addGenre")
	public ResponseEntity<Genre> addNewGenre(@RequestBody String genreName) {
		return genreService.saveGenre(genreName);
	}

	@PutMapping(path = "admin/genre/{genreID}")
	public ResponseEntity<Genre> updateGenre(@PathVariable long genreID, @RequestParam String genreName) {
		return genreService.updateGenre(genreID, genreName);
	}

	@DeleteMapping(path = "admin/genre/{genreID}")
	public ResponseEntity<String> deleteGenre(@PathVariable int genreID) {
		return genreService.deleteGenre(genreID);
	}
}
