package com.rsd_movieshop.model;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
public class Orders {

	@Id
	@SequenceGenerator(name = "order_seq", allocationSize = 1)
	@GeneratedValue(generator = "order_seq")
	@Column(name = "order_id")
	private long orderId;

	private String status;

	@OneToMany(mappedBy = "orderid")
	private List<CartItem> cartItems = new ArrayList<>();

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "userid")
	private User userid;

	
	
	public Orders() {
		super();
	}

	public Orders(String orderStatus, List<CartItem> cartItem, User user) {
		this.status = orderStatus;
		this.cartItems = cartItem;
		this.userid = user;
	}

	public long getOrderID() {
		return orderId;
	}
	
	public void setOrderID(long orderID) {
		this.orderId = orderID;
	}
	
	public String isOrderStatus() {
		return status;
	}
	
	public void setOrderStatus(String orderStatus) {
		this.status = orderStatus;
	}

	public List<CartItem> getCartItem() {
		return cartItems;
	}

	public void setCartItem(List<CartItem> cartItem) {
		this.cartItems = cartItem;
	}

	public User getUser() {
		return userid;
	}

}
