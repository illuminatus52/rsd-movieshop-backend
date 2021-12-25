package com.rsd_movieshop.model;

public class CartItemRequest {

	private String MovieName;
	private int quantity;

	public CartItemRequest() {
		super();
	}

	public CartItemRequest(String movieName, int quantity) {
		super();
		MovieName = movieName;
		this.quantity = quantity;
	}

	public String getMovieName() {
		return MovieName;
	}

	public void setMovieName(String movieName) {
		MovieName = movieName;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
