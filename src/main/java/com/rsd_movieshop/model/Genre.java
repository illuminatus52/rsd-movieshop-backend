package com.rsd_movieshop.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Genre")
public class Genre {
	@Id
	@Column(name = "genreid")
	private int genreID;

	private String movieGenre;

	@ManyToMany(mappedBy = "genres")
	private Set<Movie> movies = new HashSet<>();

	public Genre() {
	}

	public Genre(int genreID, String movieGenre) {
		this.genreID = genreID;
		this.movieGenre = movieGenre;
	}

	public Genre(String movieGenre) {
		this.movieGenre = movieGenre;
	}

	public int getGenreID() {
		return genreID;
	}
	
	public void setGenreID(int genreID) {
		this.genreID = genreID;
	}
	
	public String getMovieGenre() {
		return movieGenre;
	}
	
	public void setMovieGenre(String movieGenre) {
		this.movieGenre = movieGenre;
	}
	
	@Override
	public String toString() {
		return "Genre{" +
				"genreID=" + genreID +
				", movieGenre='" + movieGenre + '\'' +
				'}';
	}
}
