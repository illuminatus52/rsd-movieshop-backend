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
	
	public ResponseEntity<MovieResponse> saveNewMovie(Movie movie) {
		try {
			List<Genre> genres = new ArrayList<>();
			for (Genre genre : movie.getGenres()) {
				Genre exitedGenre = genreRepo.findGenreByName(genre.getName());
				if (exitedGenre != null) {
					List<Movie> movies = exitedGenre.getMovies();
					genres.add(exitedGenre);
					if (genres.size() == 0) {
						movie.setGenres(genres);
					} else {
						genres = movie.getGenres();
						genres.add(exitedGenre);
					}
					movieRepo.save(movie);
					movies.add(movie);
					exitedGenre.setMovies(movies);
					genreRepo.save(exitedGenre);
				} else {
					genreRepo.save(genre);
					Genre genre2 = genreRepo.findGenreByName(genre.getName());
					movieRepo.save(movie);
					List<Movie> movies = new ArrayList<>();
					movies.add(movie);
					genre2.setMovies(movies);
					genreRepo.save(genre2);
				}
				
			}
			MovieResponse movieResponse = new MovieResponse(movie.getTitle(), movie.getReleaseYear(), null, movie.getPrice(), movie.getMovieStock());
			List<String> genreList = new ArrayList<>();
			for (Genre genre : movie.getGenres()) {
				String name = genre.getName();
				genreList.add(name);
			}
			movieResponse.setGenres(genreList);
			return new ResponseEntity<MovieResponse>(movieResponse, HttpStatus.OK);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e.getCause());
		}
	}

	public ResponseEntity<MovieResponse> updateMovie(long id, int releaseYear, int movieStock, String title, String genres,
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
				movie.setGenres(null);
				List<String> splitGenres = Arrays.asList(genres.split(","));
				List<Genre> genreList = new ArrayList<>();
				for (String genre : splitGenres) {
					Genre genre2 = genreRepo.findGenreByName(genre);
					if (genre2 == null) {
						Genre genre3 = new Genre(genre);
						List<Movie> movies = new ArrayList<Movie>();
						movies.add(movie);
						genre3.setMovies(movies);
						genreRepo.save(genre3);
						genreList.add(genre3);
					} else {
						List<Movie> movies = genre2.getMovies();
						movies.add(movie);
						genre2.setMovies(movies);
						genreRepo.save(genre2);
						genreList.add(genre2);
					}
				}
				movie.setGenres(genreList);
				movieRepo.save(movie);
				MovieResponse movieResponse = new MovieResponse(movie.getTitle(), movie.getReleaseYear(), null, movie.getPrice(), movie.getMovieStock());
				List<String> genreStrings = new ArrayList<>();
				for (Genre genre : movie.getGenres()) {
					String name = genre.getName();
					genreStrings.add(name);
				}
				movieResponse.setGenres(genreStrings);
				return new ResponseEntity<MovieResponse>(movieResponse, HttpStatus.OK);
			} catch (Exception e) {
				throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e.getCause());
			}
		}
	}

	public ResponseEntity<String> deleteMovieById(long id) {
		if (movieRepo.findByMovieId(id) == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		} else {
			Movie movie = movieRepo.findByMovieId(id);
			List<Genre> genres = movie.getGenres();
			for (Genre genre : genres) {
				List<Movie> movies = genre.getMovies();
				movies.remove(movie);
				genre.setMovies(movies);
				genreRepo.save(genre);
			}
			movie.setGenres(null);
			movieRepo.deleteById(id);
			return new ResponseEntity<String>("The movie with id: " + id + " is deleted!", HttpStatus.OK);
		}
	}
}