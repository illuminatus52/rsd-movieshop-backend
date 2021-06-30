package com.rsd_movieshop.model;

import java.util.ArrayList;

public class Movie {
	
	private int movieID;
	private int releaseYear;
	private int movieStock;
	private String title;
	private ArrayList<Genre> genres;
	private String picture;
	private double price;

	public Movie() {
	}

	public Movie(int releaseYear, int movieStock, String title, ArrayList<Genre> genres, String picture, double price) {
		this.releaseYear = releaseYear;
		this.movieStock = movieStock;
		this.title = title;
		this.genres = genres;
		this.picture = picture;
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
	
	public ArrayList<Genre> getGenres() {
		return genres;
	}
	
	public void setGenres(ArrayList<Genre> genres) {
		this.genres = genres;
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

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
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
				", releaseYear=" + releaseYear +
				", movieStock=" + movieStock +
				", title='" + title + '\'' +
				", genres=" + genres +
				", Picture='" + picture + '\'' +
				", price=" + price +
				'}';
	}
}
