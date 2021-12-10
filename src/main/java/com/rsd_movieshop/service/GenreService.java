package com.rsd_movieshop.service;

import com.rsd_movieshop.model.Genre;
import com.rsd_movieshop.repository.GenreRepo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class GenreService {

	private final GenreRepo genreRepo;

	public GenreService(GenreRepo genreRepo) {
		this.genreRepo = genreRepo;
	}

	public ResponseEntity<Genre> findGenreByName(String genreName) {
		if (genreRepo.findGenreByMovieGenre(genreName) == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The genre " + genreName + " doesn't exist!");
		} else {
			try {
				Genre genre = genreRepo.findGenreByMovieGenre(genreName);
				return new ResponseEntity<Genre>(genre, HttpStatus.OK);
			} catch (Exception e) {
				throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e.getCause());
			}
		}
	}

	public ResponseEntity<List<Genre>> findAllGenre() {
		try {
			List<Genre> genres = genreRepo.findAll();
			return new ResponseEntity<List<Genre>>(genres, HttpStatus.OK);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e.getCause());
		}
	}

	public ResponseEntity<Genre> saveGenre(String genreName) {
		if (genreRepo.findGenreByMovieGenre(genreName) != null) {
			throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE,
					"The genre " + genreName + " exists already!");
		}
		try {
			Genre genre = new Genre(genreName);
			genreRepo.save(genre);
			return new ResponseEntity<Genre>(genre, HttpStatus.OK);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e.getCause());
		}
	}

	public ResponseEntity<Genre> updateGenre(long id, String genreName, String movies) {
		if (genreRepo.getById(id) == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The genre with id: " + id + " doesn't exist!");
		} else {
			try {
				Genre genre = genreRepo.getById(id);
				genre.setMovieGenre(genreName);
				return new ResponseEntity<Genre>(genre, HttpStatus.OK);
			} catch (Exception e) {
				throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e.getCause());
			}
		}
	}

	public ResponseEntity<String> deleteGenre(long id) {
		if (genreRepo.getById(id) == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		} else {
			genreRepo.deleteById(id);
			return new ResponseEntity<String>("The genre with the id: " + id + " is deleted!", HttpStatus.OK);
		}
	}

}
