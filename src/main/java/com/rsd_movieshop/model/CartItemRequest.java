package com.rsd_movieshop.model;

public class CartItemRequest {
	
	private long movieID;
	private int quantity;
	
	public CartItemRequest() {
		super();
	}
	
	
	public CartItemRequest(long movieID, int quantity) {
		super();
		this.movieID = movieID;
		this.quantity = quantity;
	}


	public long getMovieID() {
		return movieID;
	}


	public void setMovieID(long movieID) {
		this.movieID = movieID;
	}


	public int getQuantity() {
		return quantity;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}