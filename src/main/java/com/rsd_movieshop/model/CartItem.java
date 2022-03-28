package com.rsd_movieshop.model;

import javax.persistence.*;

@Entity
@Table(name = "items")
public class CartItem {
	
	@Id
	@SequenceGenerator(name = "item_seq", allocationSize = 1)
	@GeneratedValue(generator = "item_seq")
	@Column(name = "item_id")
	private long itemId;
	
	@OneToOne
	@JoinColumn(name = "movieid")
	private Movie movie;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "cartid")
	private Cart cart;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "orderid")
	private Orders orderid;
	
	private int quantity = 1;
	
	public CartItem() {
		super();
	}
	
	public CartItem(long itemId, Movie movie, Cart cart, Orders order, int quantity) {
		super();
		this.itemId = itemId;
		this.movie = movie;
		this.cart = cart;
		this.orderid = order;
		this.quantity = quantity;
	}
	
	public CartItem(Movie movie, Cart cart, Orders order, int quantity) {
		super();
		this.movie = movie;
		this.cart = cart;
		this.orderid = order;
		this.quantity = quantity;
	}
	
	public CartItem(Movie movie, Cart cart, int quantity) {
		super();
		this.movie = movie;
		this.cart = cart;
		this.quantity = quantity;
	}
	
	public CartItem(Movie movie, int quantity) {
		super();
		this.movie = movie;
		this.quantity = quantity;
	}

	public CartItem(Movie movie, long cartId, int quantity) {
	}

	public int getQuantity() {
		return quantity;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public Movie getMovie() {
		return movie;
	}
	
	public void setMovie(Movie movie) {
		this.movie = movie;
	}
	
	public long getItemID() {
		return itemId;
	}
	
	public void setItemID(long itemID) {
		this.itemId = itemID;
	}
	
	public Cart getCart() {
		return cart;
	}
	
	public void setCart(Cart cart) {
		this.cart = cart;
	}
	
	public Orders getOrder() {
		return orderid;
	}
	
	public void setOrder(Orders order) {
		this.orderid = order;
	}
	
}