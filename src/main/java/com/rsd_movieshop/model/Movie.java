package com.rsd_movieshop.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "Movie")
public class Movie {
	@Id
	@Column(name = "movieid")
	private int movieID;

	private int releaseYear;
	private int movieStock;
	private String title;

	@ManyToMany
	@JoinTable(
			name = "Movie_Genre",
			joinColumns = { @JoinColumn(name = "movieid")},
			inverseJoinColumns = { @JoinColumn(name = "genreid")}
	)
	private Set<Genre> genres = new HashSet<>();

	private String picture;
	private double price;

	public Movie() {
	}

	public Movie(int movieID, int releaseYear, int movieStock, String title, String picture, double price) {
		this.movieID = movieID;
		this.releaseYear = releaseYear;
		this.movieStock = movieStock;
		this.title = title;
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

	public Set<Genre> getGenres() {
		return genres;
	}

	public void setGenres(Set<Genre> genres) {
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
