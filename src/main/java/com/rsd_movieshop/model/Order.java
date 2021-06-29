package com.rsd_movieshop.model;

public class Order {
	
	private int orderID;
	private boolean orderStatus;
	private CartItem cartItem;
	
	public Order(int orderID, boolean orderStatus, CartItem cartItem) {
		this.orderID = orderID;
		this.orderStatus = orderStatus;
		this.cartItem = cartItem;
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
	
	public CartItem getCartItem() {
		return cartItem;
	}
	
	public void setCartItem(CartItem cartItem) {
		this.cartItem = cartItem;
	}
	
	@Override
	public String toString() {
		return "Order{" +
				"orderID=" + orderID +
				", orderStatus=" + orderStatus +
				", cartItem=" + cartItem +
				'}';
	}
}
