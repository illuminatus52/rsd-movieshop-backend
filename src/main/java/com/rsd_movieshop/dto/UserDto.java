package com.rsd_movieshop.dto;

public class UserDto {

	public String familyName;
	public String firstName;
	public String email;
	public String userName;
	public String password;
	
	public UserDto(String familyName, String firstName, String email, String userName, String password) {
		super();
		this.familyName = familyName;
		this.firstName = firstName;
		this.email = email;
		this.userName = userName;
		this.password = password;
	}
}
