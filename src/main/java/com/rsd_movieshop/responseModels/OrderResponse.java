package com.rsd_movieshop.responseModels;

import java.util.List;

import com.rsd_movieshop.model.CartItemRequest;
import com.rsd_movieshop.model.OrderStatus;

public class OrderResponse {

	private long orderId;
	private long userId;
	private List<CartItemRequest> items;
	private double totalPrice;
	private OrderStatus orderStatus;

	public OrderResponse() {
		super();
	}

	public OrderResponse(long orderId, long userId, List<CartItemRequest> items, double totalPrice,
			OrderStatus orderStatus) {
		super();
		this.orderId = orderId;
		this.userId = userId;
		this.items = items;
		this.totalPrice = totalPrice;
		this.orderStatus = orderStatus;
	}

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public List<CartItemRequest> getItems() {
		return items;
	}

	public void setItems(List<CartItemRequest> items) {
		this.items = items;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

}
