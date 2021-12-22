package com.rsd_movieshop.service;

import com.rsd_movieshop.model.Genre;
import com.rsd_movieshop.model.GenreResponse;
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
				Genre genre = genreRepo.findGenreByName(genreName);
				GenreResponse genreResponse = new GenreResponse(genreName, null);
				List<String> movies = new ArrayList<>();
				for (Movie movie : genre.getMovies()) {
					String movieName = movie.getTitle();
					movies.add(movieName);
				}
				genreResponse.setMovieList(movies);
				return new ResponseEntity<GenreResponse>(genreResponse, HttpStatus.OK);
			} catch (Exception e) {
				throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e.getCause());
			}
		}
	}

	public ResponseEntity<List<GenreResponse>> findAllGenre() {
		try {
			List<GenreResponse> genres = new ArrayList<>();
			for (Genre genre : genreRepo.findAll()) {
				GenreResponse genreResponse = new GenreResponse(genre.getName(), null);
				List<String> movieNames = new ArrayList<>();
				for (Movie movie: genre.getMovies()) {
					String name = movie.getTitle();
					movieNames.add(name);
				}
				genreResponse.setMovieList(movieNames);
				genres.add(genreResponse);
			}
			
			return new ResponseEntity<List<GenreResponse>>(genres, HttpStatus.OK);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e.getCause());
		}
	}

	public ResponseEntity<Genre> saveGenre(String genreName) {
		if (genreRepo.findGenreByName(genreName) != null) {
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

	public ResponseEntity<Genre> updateGenre(long id, String genreName) {
		if (genreRepo.findByGenreId(id) == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The genre with id: " + id + " doesn't exist!");
		} else {
			try {
				Genre genre = genreRepo.findByGenreId(id);
				genre.setName(genreName);
				genreRepo.save(genre);
				return new ResponseEntity<Genre>(genre, HttpStatus.OK);
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
			return new ResponseEntity<String>("The genre with the id: " + id + " is deleted!", HttpStatus.OK);
		}
	}

}
