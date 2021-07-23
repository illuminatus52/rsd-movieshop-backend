package com.rsd_movieshop.model;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
public class User {
	@Id
	@Column(name = "userid")
	private int userID;

	@OneToOne
	@JoinColumn(name = "cartid")
	private Cart userCart;

	//private Order userOrders;

	private String familyName;
	private String firstName;
	private String email;
	private String passwordHash;
	private String userName;
	private String role;

	public User() {
	}

	public User(int userID, Cart userCart, String familyName, String firstName, String email, String passwordHash, String userName, String role) {
		this.userID = userID;
		this.userCart = userCart;
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
				"userID=" + userID +
				", userCart=" + userCart +
				", familyName='" + familyName + '\'' +
				", firstName='" + firstName + '\'' +
				", email='" + email + '\'' +
				", passwordHash='" + passwordHash + '\'' +
				", userName='" + userName + '\'' +
				", role='" + role + '\'' +
				'}';
	}
}
