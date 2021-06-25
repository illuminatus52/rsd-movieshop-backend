package com.rsd_movieshop.model;

import java.util.HashMap;

public class Order {
	
	private int orderID;
	private boolean orderStatus;
	private orderItem;
	
	public Order(int orderID, boolean orderStatus, HashMap<Movie, Integer> movieOrder) {
		this.orderID = orderID;
		this.orderStatus = orderStatus;
		this.movieOrder = movieOrder;
	}
	
	public int getOrderID() {
		return orderID;
	}
	
	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}
	
	public boolean isOrderStatus() {
		return orderStatus;
	}
	
	public void setOrderStatus(boolean orderStatus) {
		this.orderStatus = orderStatus;
	}
	
	public HashMap<Movie, Integer> getMovieOrder() {
		return movieOrder;
	}
	
	public void setMovieOrder(HashMap<Movie, Integer> movieOrder) {
		this.movieOrder = movieOrder;
	}
}
