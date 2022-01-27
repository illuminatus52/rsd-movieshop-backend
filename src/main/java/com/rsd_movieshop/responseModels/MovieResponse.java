package com.rsd_movieshop.responseModels;

import java.util.List;

public class MovieResponse {

	private long movieId;
	private String name;
	private String movieUrl;
	private int releaseYear;
	private List<String> genres;
	private double price;
	private int amountInStock;

	public MovieResponse() {
		super();
	}

	public MovieResponse(long movieId, String name, int releaseYear, List<String> genres, double price, int amountInStock) {
		this.movieId = movieId;
		this.name = name;
		this.releaseYear = releaseYear;
		this.genres = genres;
		this.price = price;
		this.amountInStock = amountInStock;
	}

	public MovieResponse(String name, int releaseYear, List<String> genres, double price, int amountInStock) {
		super();
		this.name = name;
		this.releaseYear = releaseYear;
		this.genres = genres;
		this.price = price;
		this.amountInStock = amountInStock;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getReleaseYear() {
		return releaseYear;
	}

	public void setReleaseYear(int releaseYear) {
		this.releaseYear = releaseYear;
	}

	public String getMovieUrl() {
		return movieUrl;
	}

	public void setMovieUrl(String movieUrl) {
		this.movieUrl = movieUrl;
	}

	public List<String> getGenres() {
		return genres;
	}

	public void setGenres(List<String> genres) {
		this.genres = genres;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getAmountInStock() {
		return amountInStock;
	}

	public void setAmountInStock(int amountInStock) {
		this.amountInStock = amountInStock;
	}

	public long getMovieId() {
		return movieId;
	}

	public void setMovieId(long movieId) {
		this.movieId = movieId;
	}
}
