package com.rsd_movieshop.responseModels;

public class UserResponse {

	private String firstName;
	private String lastName;
	private String email;
	private String role;
	private CartResponse cart;

	public UserResponse() {
		super();
	}

	public UserResponse(String firstName, String lastName, String email, String role, CartResponse cart) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.role = role;
		this.cart = cart;
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

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public CartResponse getCart() {
		return cart;
	}

	public void setCart(CartResponse cart) {
		this.cart = cart;
	}

}
