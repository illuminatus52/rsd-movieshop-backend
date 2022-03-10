package com.rsd_movieshop.responseModels;

public class CartItemResponse {

	private long cartItemID;
	private String movie;
	private int amount;

	public CartItemResponse() {
		super();
	}

	public CartItemResponse(String movie, int amount) {
		super();
		this.movie = movie;
		this.amount = amount;
	}

	public CartItemResponse(long cartItemID, String movie, int amount) {
		super();
		this.cartItemID = cartItemID;
		this.movie = movie;
		this.amount = amount;
	}

	public long getCartItemID() {
		return cartItemID;
	}

	public void setCartItemID(long cartItemID) {
		this.cartItemID = cartItemID;
	}

	public String getMovie() {
		return movie;
	}

	public void setMovie(String movie) {
		this.movie = movie;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}
}