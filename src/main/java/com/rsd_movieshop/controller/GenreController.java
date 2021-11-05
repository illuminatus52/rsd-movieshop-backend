package com.rsd_movieshop.controller;

import com.rsd_movieshop.model.Genre;
import com.rsd_movieshop.service.GenreService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/genre")
public class GenreController {

	private final GenreService genreService;


	public GenreController(GenreService genreService) {
		this.genreService = genreService;
	}


	@GetMapping("/{genreID}")
	public Optional<Genre> getGenre (@PathVariable int genreID){
		System.out.println("GenreController: ");
		return genreService.findGenreById(genreID);
	}
	
	@GetMapping
	public List<Genre> getGenres() {
		System.out.println("GenreController: getAllOrders");
		return genreService.findAllGenre();
	}
	
	@PostMapping
	public void addNewGenre(@RequestBody Genre genre) {
		genreService.saveGenre(genre);
		System.out.println("GenreController: addNewGenre");
	}
	
	@PutMapping(path = "/{genreID}")
	public void updateGenre(@PathVariable int genreID) {
		System.out.println("GenreController: updateGenre");
	}
	
	@DeleteMapping(path = "/{genreID}")
	public void deleteGenre(@PathVariable int genreID) {
		genreService.deleteGenre(genreID);
		System.out.println("GenreController: deleteGenre");
	}
}
