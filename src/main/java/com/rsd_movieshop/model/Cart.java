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
    
    @OneToOne
    @JoinColumn(name = "userid")
    private User user;

    @OneToMany(mappedBy = "cart")
    private List<CartItem> cartItems =  new ArrayList<>();

    
    
	public Cart() {
		super();
	}



	public Cart(long cartId, User user, List<CartItem> cartItems) {
		super();
		this.cartId = cartId;
		this.user = user;
		this.cartItems = cartItems;
	}





	public Cart(User user, List<CartItem> cartItems) {
		super();
		this.user = user;
		this.cartItems = cartItems;
	}



	public Cart(User user) {
		super();
		this.user = user;
	}



	public long getCartId() {
		return cartId;
	}



	public void setCartId(long cartId) {
		this.cartId = cartId;
	}



	public User getUser() {
		return user;
	}



	public void setUser(User user) {
		this.user = user;
	}



	public List<CartItem> getCartItems() {
		return cartItems;
	}



	public void setCartItems(List<CartItem> cartItems) {
		this.cartItems = cartItems;
	}

    

}
