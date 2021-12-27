package com.rsd_movieshop.responseModels;

import java.util.List;

public class CartResponse {

	private long cartId;
	private List<String> items;

	public CartResponse() {
		super();
	}

	public CartResponse(long cartId, List<String> items) {
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

	public List<String> getItems() {
		return items;
	}

	public void setItems(List<String> items) {
		this.items = items;
	}

}
