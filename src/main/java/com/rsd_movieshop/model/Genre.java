package com.rsd_movieshop.model;

public class Genre {
	
	private int genreID;
	private String movieGenre;
	
	public Genre(int genreID, String movieGenre) {
		this.genreID = genreID;
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
