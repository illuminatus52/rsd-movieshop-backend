package com.rsd_movieshop.model;

public class Movie {
	
	private int movieID, releaseYear, movieStock;
	private String title;
	private Genre genre;
	private double price;
	
	public Movie(int movieID, String title, String genre, int releaseYear, int movieStock, double price) {
		this.movieID = movieID;
		this.title = title;
		this.genre = genre;
		this.releaseYear = releaseYear;
		this.movieStock = movieStock;
		this.price = price;
	}
	
	public int getMovieID() {
		return movieID;
	}
	
	public void setMovieID(int movieID) {
		this.movieID = movieID;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getGenre() {
		return genre;
	}
	
	public void setGenre(String genre) {
		this.genre = genre;
	}
	
	public int getReleaseYear() {
		return releaseYear;
	}
	
	public void setReleaseYear(int releaseYear) {
		this.releaseYear = releaseYear;
	}
	
	public int getMovieStock() {
		return movieStock;
	}
	
	public void setMovieStock(int movieStock) {
		this.movieStock = movieStock;
	}
	
	public double getPrice() {
		return price;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	
	@Override
	public String toString() {
		return "Movie{" +
				"movieID=" + movieID +
				", title='" + title + '\'' +
				", genre='" + genre + '\'' +
				", releaseYear=" + releaseYear +
				", movieStock=" + movieStock +
				", price=" + price +
				'}';
	}
}
