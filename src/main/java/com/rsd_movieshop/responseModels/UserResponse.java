package com.rsd_movieshop.responseModels;


public class UserResponse {
	
	private long id;
	private String firstName;
	private String lastName;
	private String email;
	private String role;
	private CartResponse cart;
	private boolean isEnabled;
	
	public UserResponse() {
		super();
	}
	
	
	public UserResponse(long id, String firstName, String lastName, String email, String role, CartResponse cart,
			boolean isEnabled) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.role = role;
		this.cart = cart;
		this.isEnabled = isEnabled;
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

	public boolean isEnabled() {
		return isEnabled;
	}

	public void setEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
	}
}