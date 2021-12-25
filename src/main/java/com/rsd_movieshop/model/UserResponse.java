package com.rsd_movieshop.model;

public class UserResponse {

	private String firstName;
	private String lastName;
	private String email;
	private String role;
	private CartResponse cartResponse;

	public UserResponse() {
		super();
	}

	public UserResponse(String firstName, String lastName, String email, String role, CartResponse cartResponse) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.role = role;
		this.cartResponse = cartResponse;
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

	public CartResponse getCartResponse() {
		return cartResponse;
	}

	public void setCartResponse(CartResponse cartResponse) {
		this.cartResponse = cartResponse;
	}

}
