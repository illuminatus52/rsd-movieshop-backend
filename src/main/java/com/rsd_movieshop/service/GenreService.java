package com.rsd_movieshop.service;

import com.rsd_movieshop.model.Genre;
import com.rsd_movieshop.responseModels.GenreResponse;
import com.rsd_movieshop.model.Movie;
import com.rsd_movieshop.repository.GenreRepo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class GenreService {

	private final GenreRepo genreRepo;

	public GenreService(GenreRepo genreRepo) {
		this.genreRepo = genreRepo;
	}

	public ResponseEntity<GenreResponse> findGenreByName(String genreName) {
		if (genreRepo.findGenreByName(genreName) == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The genre " + genreName + " doesn't exist!");
		} else {
			try {
				GenreResponse genreResponse = getGenreResponse(genreRepo.findGenreByName(genreName));
				return new ResponseEntity<>(genreResponse, HttpStatus.OK);
			} catch (Exception e) {
				throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e.getCause());
			}
		}
	}

	public ResponseEntity<List<GenreResponse>> findAllGenre() {
		try {
			List<GenreResponse> genres = new ArrayList<>();
			for (Genre genre : genreRepo.findAll()) {
				GenreResponse genreResponse = getGenreResponse(genre);
				genres.add(genreResponse);
			}

			return new ResponseEntity<>(genres, HttpStatus.OK);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e.getCause());
		}
	}

	public ResponseEntity<GenreResponse> saveGenre(String genreName) {
		if (genreRepo.findGenreByName(genreName) != null) {
			throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "The genre " + genreName + " exists already!");
		}
		try {
			Genre genre = new Genre(genreName);
			genreRepo.save(genre);
			GenreResponse genreResponse = getGenreResponse(genre);
			return new ResponseEntity<>(genreResponse, HttpStatus.OK);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e.getCause());
		}
	}

	public ResponseEntity<GenreResponse> updateGenre(long id, String genreName) {
		if (genreRepo.findByGenreId(id) == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The genre with id: " + id + " doesn't exist!");
		} else {
			try {
				Genre genre = genreRepo.findByGenreId(id);
				genre.setName(genreName);
				genreRepo.save(genre);
				GenreResponse genreResponse = getGenreResponse(genre);
				return new ResponseEntity<>(genreResponse, HttpStatus.OK);
			} catch (Exception e) {
				throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e.getCause());
			}
		}
	}

	public ResponseEntity<String> deleteGenre(long id) {
		if (genreRepo.findByGenreId(id) == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		} else {
			genreRepo.deleteById(id);
			return new ResponseEntity<>("The genre with the id: " + id + " is deleted!", HttpStatus.OK);
		}
	}

	public GenreResponse getGenreResponse(Genre genre) {
		GenreResponse genreResponse = new GenreResponse(genre.getName(), null);
		List<String> movies = new ArrayList<>();
		for (Movie movie : genre.getMovies()) {
			String movieName = movie.getTitle();
			movies.add(movieName);
		}
		genreResponse.setMovieList(movies);
		return genreResponse;
	}
}
