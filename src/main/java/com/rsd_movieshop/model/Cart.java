package com.rsd_movieshop.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cart")
public class Cart {
	
	@Id
	@SequenceGenerator(name = "cart_seq", allocationSize = 1)
	@GeneratedValue(generator = "cart_seq")
	@Column(name = "cartid")
	private long cartId;
	
	@OneToMany(mappedBy = "cart")
	private List<CartItem> cartItems = new ArrayList<>();
	
	public Cart() {
		super();
	}
	
	public Cart(long cartId, List<CartItem> cartItems) {
		super();
		this.cartId = cartId;
		this.cartItems = cartItems;
	}
	
	public Cart(List<CartItem> cartItems) {
		super();
		this.cartItems = cartItems;
	}
	
	public long getCartId() {
		return cartId;
	}
	
	public void setCartId(long cartId) {
		this.cartId = cartId;
	}
	
	public List<CartItem> getCartItems() {
		return cartItems;
	}
	
	public void setCartItems(List<CartItem> cartItems) {
		this.cartItems = cartItems;
	}
}