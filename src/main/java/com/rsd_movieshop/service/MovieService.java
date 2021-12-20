package com.rsd_movieshop.service;

import com.rsd_movieshop.model.Genre;
import com.rsd_movieshop.model.Movie;
import com.rsd_movieshop.model.MovieResponse;
import com.rsd_movieshop.repository.GenreRepo;
import com.rsd_movieshop.repository.MovieRepo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class MovieService {

	private final MovieRepo movieRepo;
	private final GenreRepo genreRepo;

	public MovieService(MovieRepo movieRepo, GenreRepo genreRepo) {
		this.movieRepo = movieRepo;
		this.genreRepo = genreRepo;
	}

	public ResponseEntity<MovieResponse> findMovieById(long id) {
		if (movieRepo.findByMovieId(id) == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The movie with id: " + id + " doesn't exist!");
		} else {
			Movie movie = movieRepo.findByMovieId(id);
			MovieResponse movieResponse = new MovieResponse(movie.getTitle(), movie.getReleaseYear(), null,
					movie.getPrice(), movie.getMovieStock());
			List<String> genreList = new ArrayList<>();
			for (Genre genre : movie.getGenres()) {
				String name = genre.getName();
				genreList.add(name);
			}
			movieResponse.setGenres(genreList);
			return new ResponseEntity<MovieResponse>(movieResponse, HttpStatus.OK);
		}
	}

	public ResponseEntity<List<MovieResponse>> findMovies() {
		try {
			List<MovieResponse> movies = new ArrayList<>();
			for (Movie movie : movieRepo.findAll()) {
				MovieResponse movieResponse = new MovieResponse(movie.getTitle(), movie.getReleaseYear(), null,
						movie.getPrice(), movie.getMovieStock());
				List<String> genreList = new ArrayList<>();
				for (Genre genre : movie.getGenres()) {
					String name = genre.getName();
					genreList.add(name);
				}
				movieResponse.setGenres(genreList);
				movies.add(movieResponse);
			}
			return new ResponseEntity<List<MovieResponse>>(movies, HttpStatus.OK);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e.getCause());
		}
	}

	public ResponseEntity<Movie> saveNewMovie(Movie movie) {
		try {
			List<Genre> genres = movie.getGenres();
			List<Movie> movies = new ArrayList<Movie>();
			movies.add(movie);
			if (genres.size() != 0) {
				for (Genre genre : genres) {
					Genre genre2 = genreRepo.findGenreByName(genre.getName());
					if (genre2 == null) {
						Genre newGenre = new Genre(genre.getName());
						newGenre.setMovies(movies);
					} else {
						genre2.setMovies(movies);
					}
				}
			}
			movieRepo.save(movie);
			return new ResponseEntity<Movie>(movie, HttpStatus.OK);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e.getCause());
		}
	}

	public ResponseEntity<Movie> updateMovie(long id, int releaseYear, int movieStock, String title, String genres,
			String pic, double price) {
		if (movieRepo.findByMovieId(id) == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The movie with id: " + id + " doesn't exist!");
		} else {
			try {
				Movie movie = movieRepo.findByMovieId(id);
				movie.setReleaseYear(releaseYear);
				movie.setMovieStock(movieStock);
				movie.setTitle(title);
				movie.setPicture(pic);
				movie.setPrice(price);
				List<String> splitGenres = Arrays.asList(genres.split(","));
				for (String genre : splitGenres) {
					Genre genre2 = genreRepo.findGenreByName(genre);
					if (genre2 == null) {
						Genre genre3 = new Genre(genre);
						List<Movie> movies = new ArrayList<Movie>();
						movies.add(movie);
						genre3.setMovies(movies);
					} else {
						List<Movie> movies = new ArrayList<Movie>();
						movies.add(movie);
						genre2.setMovies(movies);
					}
				}
				return new ResponseEntity<Movie>(movie, HttpStatus.OK);
			} catch (Exception e) {
				throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e.getCause());
			}
		}
	}

	public ResponseEntity<String> deleteMovieById(long id) {
		if (movieRepo.findByMovieId(id) == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		} else {
			movieRepo.deleteById(id);
			return new ResponseEntity<String>("The movie with id: " + id + " is deleted!", HttpStatus.OK);
		}
	}
}