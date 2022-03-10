package com.rsd_movieshop.dto;

public class UserDto {

	public String lastName;
	public String firstName;
	public String email;
	public String userName;
	public String password;
	public String picture;
	public String shippingAddress;
	

	public UserDto(String lastName, String firstName, String email, String userName, String password, String picture,
			String shippingAddress) {
		super();
		this.lastName = lastName;
		this.firstName = firstName;
		this.email = email;
		this.userName = userName;
		this.password = password;
		this.picture = picture;
		this.shippingAddress = shippingAddress;
	}
}
