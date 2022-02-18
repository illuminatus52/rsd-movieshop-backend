package com.rsd_movieshop.model;

import javax.persistence.*;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


import java.util.*;

@Entity
@Table(name = "Movie")
public class Movie {
	
	@Id
	@SequenceGenerator(name = "movie_seq", allocationSize = 1)
	@GeneratedValue(generator = "movie_seq")
	@Column(name = "movie_id")
	private long movieId;
	private String imdbId;
	
	private int releaseYear;
	private int movieStock;
	@NotNull(message = "Movie title is mandatory")
	@NotEmpty(message = "Movie title is mandatory")
	@NotBlank(message = "Movie title is mandatory")
	private String title;
	
	@ManyToMany(cascade = {CascadeType.PERSIST}, fetch = FetchType.EAGER)
	@JoinTable(name = "movie_genre", joinColumns = @JoinColumn(name = "movieid"),
			inverseJoinColumns = @JoinColumn(name = "genreid"))
	private List<Genre> genres = new ArrayList<>();
	
	private String picture;
	
	@DecimalMin(value = "0.1", message = "price is too low!")
	@DecimalMax(value = "1000", message = "price is too high!")
	private double price;
	
	public Movie() {
		super();
	}
	
	public Movie(int releaseYear, int movieStock, String title, List<Genre> genres, String picture, double price) {
		this.releaseYear = releaseYear;
		this.movieStock = movieStock;
		this.title = title;
		this.genres = genres;
		this.picture = picture;
		this.price = price;
	}
	
	
	
	public Movie(String imdbId, int releaseYear, int movieStock,
			@NotNull(message = "Movie title is mandatory") @NotEmpty(message = "Movie title is mandatory") @NotBlank(message = "Movie title is mandatory") String title,
			List<Genre> genres, String picture,
			@DecimalMin(value = "0.1", message = "price is too low!") @DecimalMax(value = "1000", message = "price is too high!") double price) {
		super();
		this.imdbId = imdbId;
		this.releaseYear = releaseYear;
		this.movieStock = movieStock;
		this.title = title;
		this.genres = genres;
		this.picture = picture;
		this.price = price;
	}

	public long getMovieID() {
		return movieId;
	}
	
	public void setMovieID(long movieID) {
		this.movieId = movieID;
	}
	
	public String getImdbId() {
		return imdbId;
	}

	public void setImdbId(String imdbId) {
		this.imdbId = imdbId;
	}

	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public List<Genre> getGenres() {
		return genres;
	}
	
	public void setGenres(List<Genre> genres) {
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
}