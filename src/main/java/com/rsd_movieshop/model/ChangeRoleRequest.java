package com.rsd_movieshop.model;

public class ChangeRoleRequest {

	private String username;
	private String role;

	public String getUsername() {
		return username;

	}

	public ChangeRoleRequest() {
		super();
	}

	public ChangeRoleRequest(String username, String role) {
		super();
		this.username = username;
		this.role = role;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}
