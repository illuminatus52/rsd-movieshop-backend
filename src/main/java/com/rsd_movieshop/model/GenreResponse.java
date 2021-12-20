package com.rsd_movieshop.model;

import java.util.List;

/**
 * @author Rajeh ABDULHADI
 *
 */
public class GenreResponse {

	private String name;
	private List<String> movieList;

	public GenreResponse(String name, List<String> movieList) {
		super();
		this.name = name;
		this.movieList = movieList;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getMovieList() {
		return movieList;
	}

	public void setMovieList(List<String> movieList) {
		this.movieList = movieList;
	}

}
