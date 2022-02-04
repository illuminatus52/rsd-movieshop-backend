package com.rsd_movieshop.service;

import com.rsd_movieshop.model.Genre;
import com.rsd_movieshop.model.Movie;
import com.rsd_movieshop.model.MovieRequest;
import com.rsd_movieshop.responseModels.MovieResponse;
import com.rsd_movieshop.repository.GenreRepo;
import com.rsd_movieshop.repository.MovieRepo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
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
			MovieResponse movieResponse = getMovieResponse(movieRepo.findByMovieId(id));
			return new ResponseEntity<>(movieResponse, HttpStatus.OK);
		}
	}
	
	public ResponseEntity<List<MovieResponse>> findMovies() {
		try {
			List<MovieResponse> movies = new ArrayList<>();
			for (Movie movie : movieRepo.findAll()) {
				MovieResponse movieResponse = getMovieResponse(movie);
				movies.add(movieResponse);
			}
			return new ResponseEntity<>(movies, HttpStatus.OK);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e.getCause());
		}
	}
	
	public ResponseEntity<MovieResponse> saveNewMovie(Movie movie) {
		try {
			List<Genre> genres = new ArrayList<>();
			
			for (Genre genre : movie.getGenres()) {
				Genre genre2 = genreRepo.findGenreByName(genre.getName());
				
				if (genre2 != null) {
					List<Movie> movies = genre2.getMovies();
					movies.add(movie);
					genre2.setMovies(movies);
					genres.add(genre2);
				}
			}
			
			if (genres.size() == movie.getGenres().size()) {
				movie.setGenres(genres);
			}
			
			if (genres.size() < movie.getGenres().size() && genres.size() > 0) {
				List<Genre> genreList = movie.getGenres();
				List<Genre> toRemove = new ArrayList<>();
				List<Genre> toAdd = new ArrayList<>();
				
				for (Genre genre : genreList) {
					
					for (Genre genre1 : genres) {
						
						if (genre.getName().equalsIgnoreCase(genre1.getName())) {
							toRemove.add(genre);
							toAdd.add(genre1);
						}
					}
				}
				genreList.removeAll(toRemove);
				genreList.addAll(toAdd);
				movie.setGenres(genreList);
			}
			movieRepo.save(movie);
			MovieResponse movieResponse = getMovieResponse(movie);
			
			return new ResponseEntity<>(movieResponse, HttpStatus.OK);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e.getCause());
		}
	}
	
	public ResponseEntity<MovieResponse> updateMovie(long id, MovieRequest movieRequest) {
		if (movieRepo.findByMovieId(id) == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The movie with id: " + id + " doesn't exist!");
		} else {
			try {
				Movie movie = movieRepo.findByMovieId(id);
				
				if (movieRequest.getReleaseYear() > 0) {
					movie.setReleaseYear(movieRequest.getReleaseYear());
				}
				
				if (movieRequest.getName() != null) {
					movie.setTitle(movieRequest.getName());
				}
				
				if (movieRequest.getStock() > 0) {
					movie.setMovieStock(movieRequest.getStock());
				}
				
				if (movieRequest.getPicture() != null) {
					movie.setPicture(movieRequest.getPicture());
				}
				
				if (movieRequest.getPrice() > 0) {
					movie.setPrice(movieRequest.getPrice());
				}
				String genres = movieRequest.getGenres();
				
				if (genres != null) {
					movie.setGenres(null);
					String[] splitGenres = genres.split(",");
					List<Genre> genreList = new ArrayList<>();
					
					for (String genre : splitGenres) {
						Genre genre2 = genreRepo.findGenreByName(genre);
						
						if (genre2 == null) {
							Genre genre3 = new Genre(genre);
							List<Movie> movies = new ArrayList<>();
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
				}
				movieRepo.save(movie);
				MovieResponse movieResponse = getMovieResponse(movie);
				return new ResponseEntity<>(movieResponse, HttpStatus.OK);
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
			return new ResponseEntity<>("The movie with id: " + id + " is deleted!", HttpStatus.OK);
		}
	}
	
	public MovieResponse getMovieResponse(Movie movie) {
		MovieResponse movieResponse = new MovieResponse(
				movie.getMovieID(),
				movie.getTitle(),
				movie.getReleaseYear(),
				null,
				movie.getPrice(),
				movie.getMovieStock());
		
		movieResponse.setMovieUrl(movie.getPicture());
		
		List<String> genreList = new ArrayList<>();
		for (Genre genre : movie.getGenres()) {
			String name = genre.getName();
			genreList.add(name);
		}
		movieResponse.setGenres(genreList);
		return movieResponse;
	}
}