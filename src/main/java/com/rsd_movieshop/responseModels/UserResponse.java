package com.rsd_movieshop.responseModels;

import com.rsd_movieshop.model.Role;

public class UserResponse {

	private long id;
	private String username;
	private String firstName;
	private String lastName;
	private String email;
	private Role role;
	private CartResponse cart;
	private boolean isEnabled;
	private String picture;
	private String shippingAddress;

	public UserResponse() {
		super();
	}

	public UserResponse(long id, String username, String firstName, String lastName, String email, Role role,
			CartResponse cart, boolean isEnabled, String picture, String shippingAddress) {
		super();
		this.id = id;
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.role = role;
		this.cart = cart;
		this.isEnabled = isEnabled;
		this.picture = picture;
		this.shippingAddress = shippingAddress;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public CartResponse getCart() {
		return cart;
	}

	public void setCart(CartResponse cart) {
		this.cart = cart;
	}

	public boolean isEnabled() {
		return isEnabled;
	}

	public void setEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

}