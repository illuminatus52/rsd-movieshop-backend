package com.rsd_movieshop;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.rsd_movieshop.model.Genre;
import com.rsd_movieshop.model.Movie;
import com.rsd_movieshop.model.MovieRequest;
import com.rsd_movieshop.repository.MovieRepo;
import com.rsd_movieshop.service.MovieService;

@SpringBootTest
@ActiveProfiles("h2")
@Transactional
public class MovieServiceTest {

	@Autowired
	private MovieRepo movieRepo;

	@Autowired
	private MovieService movieService;

	@BeforeEach
	public void init() {
		Movie movie01 = new Movie(2000, 2, "Movie-01", null, null, 4.6);
		Genre genre01 = new Genre("Genre-01");
		Genre genre02 = new Genre("Genre-02");
		List<Genre> genres01 = new ArrayList<>();
		genres01.add(genre01);
		genres01.add(genre02);
		movie01.setGenres(genres01);
		movieService.saveNewMovie(movie01);

		Movie movie02 = new Movie(2001, 3, "Movie-02", null, null, 5.99);
		Genre genre03 = new Genre("Genre-03");
		Genre genre04 = new Genre("Genre-04");
		List<Genre> genres02 = new ArrayList<>();
		genres02.add(genre03);
		genres02.add(genre04);
		movie02.setGenres(genres02);
		movieService.saveNewMovie(movie02);

		Movie movie03 = new Movie(2001, 3, "Movie-03", null, null, 9);
		List<Genre> genres03 = new ArrayList<>();
		genres03.add(genre01);
		genres03.add(genre04);
		movie03.setGenres(genres03);
		movieService.saveNewMovie(movie03);

	}

	@Test
	public void testAddMovie() {
		assertTrue(movieRepo.findByMovieId(7).getTitle().equalsIgnoreCase("Movie-01"));
		assertTrue(movieRepo.findByMovieId(8).getGenres().get(0).getName().equalsIgnoreCase("Genre-03"));
		assertTrue(movieRepo.findByMovieId(9).getTitle().equalsIgnoreCase("Movie-03"));
		assertTrue(movieRepo.findByMovieId(9).getGenres().get(0).getMovies().get(0).getTitle()
				.equalsIgnoreCase("Movie-03"));
	}

	@Test
	public void testGetMovies() {
		List<Movie> movies = movieRepo.findAll();
		assertTrue(movies.get(0).getTitle().equalsIgnoreCase("Movie-01"));
		assertTrue(movies.get(1).getTitle().equalsIgnoreCase("Movie-02"));
		assertTrue(movies.get(2).getTitle().equalsIgnoreCase("Movie-03"));

	}

	@Test
	public void deleteMovie() {
		movieService.deleteMovieById(1);
		assertFalse(movieRepo.findByMovieId(1) != null);
		assertTrue(movieRepo.findByMovieId(2).getMovieStock() == 3);
	}
	
	@Test
	public void updateMovie() {
		MovieRequest movieRequest = new MovieRequest("Movie-01", 2002, 3, "Genre-01,Genre-03", null, 4.99);
		movieService.updateMovie(4, movieRequest);
		assertTrue(movieRepo.findByMovieId(4).getMovieStock() == 3);
		assertTrue(movieRepo.findByMovieId(4).getPrice() == 4.99);
		
	}

}
