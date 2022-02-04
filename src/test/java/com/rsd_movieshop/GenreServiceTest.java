package com.rsd_movieshop;

import static org.junit.Assert.assertTrue;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.rsd_movieshop.model.Genre;
import com.rsd_movieshop.repository.GenreRepo;
import com.rsd_movieshop.service.GenreService;

@SpringBootTest
@ActiveProfiles("h2")
public class GenreServiceTest {
	
	@Autowired
	private GenreRepo genreRepo;
	
	@Autowired
	private GenreService genreService;
	
	
	@BeforeEach
	public void init() {
		
		genreService.saveGenre("Genre-1");
		genreService.saveGenre("Genre-2");
	}

	@Test
	public void findAndDeleteGenreTest() {
		Genre genre = genreRepo.findGenreByName("Genre-1");
		Genre genre1 = genreRepo.findGenreByName("Genre-2");
		assertTrue(genre.getName().equalsIgnoreCase("Genre-1"));
		assertTrue(genre1.getName().equalsIgnoreCase("Genre-2"));
		assertTrue(genreRepo.findAll().size() == 2);
		genreService.deleteGenre(1);
		genreService.deleteGenre(2);
		assertTrue(genreRepo.findAll().size() == 0);
	}
	
	@Test
	public void updateGenreTest() {
		genreService.updateGenre(3, "Genre-3");
		assertTrue(genreRepo.findByGenreId(3).getName().equalsIgnoreCase("Genre-3"));
	}
	
}
