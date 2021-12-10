package com.rsd_movieshop.model;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * class cannot named "order" bcuz it is a keyword in SQL.
 */
@Entity
@Table(name = "orders")
public class Orders {

	@Id
	@SequenceGenerator(name = "order_seq", allocationSize = 1)
	@GeneratedValue(generator = "order_seq")
	@Column(name = "order_id")
	private long orderId;

	@Enumerated(EnumType.STRING)
	private OrderStatus status = OrderStatus.In_process;

	@OneToMany(mappedBy = "orderid")
	private List<CartItem> cartItems = new ArrayList<>();
	
	private double totalPrice;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "userid")
	private User userid;

	
	
	public Orders() {
		super();
	}
	
	

	public Orders(List<CartItem> cartItems, User userid) {
		super();
		this.cartItems = cartItems;
		this.userid = userid;
	}



	public Orders(OrderStatus orderStatus, List<CartItem> cartItem, User user) {
		this.status = orderStatus;
		this.cartItems = cartItem;
		this.userid = user;
	}
	
	

	public Orders(OrderStatus status, List<CartItem> cartItems, double totalPrice, User userid) {
		super();
		this.status = status;
		this.cartItems = cartItems;
		this.totalPrice = totalPrice;
		this.userid = userid;
	}



	public long getOrderId() {
		return orderId;
	}



	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}



	public OrderStatus getStatus() {
		return status;
	}



	public void setStatus(OrderStatus status) {
		this.status = status;
	}



	public List<CartItem> getCartItems() {
		return cartItems;
	}



	public void setCartItems(List<CartItem> cartItems) {
		this.cartItems = cartItems;
	}



	public double getTotalPrice() {
		return totalPrice;
	}



	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}



	public User getUserid() {
		return userid;
	}



	public void setUserid(User userid) {
		this.userid = userid;
	}

}
