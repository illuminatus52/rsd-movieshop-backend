package com.rsd_movieshop.model;

import java.util.ArrayList;

public class User {
	
	private int userID;
	private Cart userCart;
	private ArrayList<Order> userOrders;
	private String familyName;
	private String firstName;
	private String email;
	private String passwordHash;
	private String userName;
	private String role;

	public User() {
	}

	public User(Cart userCart, ArrayList<Order> userOrders,
				String familyName, String firstName, String email,
				String passwordHash, String userName, String role) {
		this.userCart = userCart;
		this.userOrders = userOrders;
		this.familyName = familyName;
		this.firstName = firstName;
		this.email = email;
		this.passwordHash = passwordHash;
		this.userName = userName;
		this.role = role;
	}

	
	public int getUserID() {
		return userID;
	}
	
	public void setUserID(int userID) {
		this.userID = userID;
	}
	
	public Cart getUserCart() {
		return userCart;
	}
	
	public void setUserCart(Cart userCart) {
		this.userCart = userCart;
	}
	
	public ArrayList<Order> getUserOrders() {
		return userOrders;
	}
	
	public void setUserOrders(ArrayList<Order> userOrders) {
		this.userOrders = userOrders;
	}
	
	public String getFamilyName() {
		return familyName;
	}
	
	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPasswordHash() {
		return passwordHash;
	}
	
	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getRole() {
		return role;
	}
	
	public void setRole(String role) {
		this.role = role;
	}
	
	@Override
	public String toString() {
		return "User{" +
				"id=" + userID +
				", userCart=" + userCart +
				", userOrders=" + userOrders +
				", familyName='" + familyName + '\'' +
				", firstName='" + firstName + '\'' +
				", email='" + email + '\'' +
				", passwordHash='" + passwordHash + '\'' +
				", userName='" + userName + '\'' +
				", role='" + role + '\'' +
				'}';
	}
}
