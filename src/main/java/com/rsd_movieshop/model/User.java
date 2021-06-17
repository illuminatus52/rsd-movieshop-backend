package com.rsd_movieshop.model;

public class User {
	private int userID;
	private String familyName, firstName, email, passwordHash;
	
	public User(int userID, String familyName, String firstName, String email, String passwordHash) {
		this.userID = userID;
		this.familyName = familyName;
		this.firstName = firstName;
		this.email = email;
		this.passwordHash = passwordHash;
	}
	
	public int getUserID() {
		return userID;
	}
	
	public void setUserID(int userID) {
		this.userID = userID;
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
}
