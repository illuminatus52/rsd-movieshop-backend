package com.rsd_movieshop.responseModels;

import java.util.List;

public class CartResponse {

	private long cartId;
	private List<CartItemResponse> items;
	
	public CartResponse() {
		super();
	}
	
	public CartResponse(long cartId, List<CartItemResponse> items) {
		super();
		this.cartId = cartId;
		this.items = items;
	}
	
	
	
	public long getCartId() {
		return cartId;
	}
	
	public void setCartId(long cartId) {
		this.cartId = cartId;
	}
	
	public List<CartItemResponse> getItems() {
		return items;
	}
	
	public void setItems(List<CartItemResponse> items) {
		this.items = items;
	}
}