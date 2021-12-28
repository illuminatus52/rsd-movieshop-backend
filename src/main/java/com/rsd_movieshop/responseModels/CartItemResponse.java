package com.rsd_movieshop.responseModels;

public class CartItemResponse {

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
	};
}