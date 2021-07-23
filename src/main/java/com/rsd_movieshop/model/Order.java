package com.rsd_movieshop.model;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Bestellung")
public class Order {
	@Id
	private int orderID;

	private boolean orderStatus;

	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "itemid")
	private List<CartItem> cartItem = new ArrayList<>();

	@ManyToOne
	@JoinColumn(name = "userid")
	private User user;

	public Order(int orderID, boolean orderStatus, List<CartItem> cartItem, User user) {
		this.orderID = orderID;
		this.orderStatus = orderStatus;
		this.cartItem = cartItem;
		this.user = user;
	}

	public Order() {
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

	public List<CartItem> getCartItem() {
		return cartItem;
	}

	public void setCartItem(List<CartItem> cartItem) {
		this.cartItem = cartItem;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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
