package com.rsd_movieshop.model;

public class MovieRequest {
	
	private String imdbID;
	private String name;
	private int releaseYear;
	private int stock;
	private String genres;
	private String picture;
	private double price;
	
	public MovieRequest() {
		super();
	}
	
	
	public MovieRequest(String imdbID, String name, int releaseYear, int stock, String genres, String picture,
			double price) {
		super();
		this.imdbID = imdbID;
		this.name = name;
		this.releaseYear = releaseYear;
		this.stock = stock;
		this.genres = genres;
		this.picture = picture;
		this.price = price;
	}


	public String getImdbID() {
		return imdbID;
	}

	public void setImdbID(String imdbID) {
		this.imdbID = imdbID;
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
	
	public int getStock() {
		return stock;
	}
	
	public void setStock(int stock) {
		this.stock = stock;
	}
	
	public String getGenres() {
		return genres;
	}
	
	public void setGenres(String genres) {
		this.genres = genres;
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
}