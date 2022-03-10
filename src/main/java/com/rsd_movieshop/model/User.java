package com.rsd_movieshop.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user")
public class User {

	@Id
	@SequenceGenerator(name = "userid_seq", allocationSize = 1)
	@GeneratedValue(generator = "userid_seq")
	@Column(name = "user_id")
	private long userId;

	@OneToMany(mappedBy = "userid", cascade = CascadeType.REMOVE)
	private List<Orders> orders = new ArrayList<Orders>();

	private String familyName;
	private String firstName;
	@NotNull(message = "Email is mandatory")
	@NotEmpty(message = "Email is mandatory")
	@NotBlank(message = "Email is mandatory")
	private String email;

	@NotNull(message = "Username is mandatory")
	@NotEmpty(message = "Username is mandatory")
	@NotBlank(message = "Username is mandatory")
	@Column(nullable = false, unique = true)
	private String username;

	@NotNull(message = "Password is mandatory")
	@NotEmpty(message = "Password is mandatory")
	@NotBlank(message = "Password is mandatory")
	@JsonIgnore
	@Column(nullable = false)
	private String password;
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private Role role = Role.ROLE_USER;
	private boolean isEnabled = true;

	@OneToOne(cascade = { CascadeType.PERSIST, CascadeType.REMOVE })
	@JoinColumn(name = "cart")
	private Cart cart = new Cart();

	private String picture;
	private String shippingAddress;

	public User() {
		super();
	}

	public User(long userId, String familyName, String firstName,
			@NotNull(message = "Email is mandatory") @NotEmpty(message = "Email is mandatory") @NotBlank(message = "Email is mandatory") String email,
			@NotNull(message = "Username is mandatory") @NotEmpty(message = "Username is mandatory") @NotBlank(message = "Username is mandatory") String username,
			@NotNull(message = "Password is mandatory") @NotEmpty(message = "Password is mandatory") @NotBlank(message = "Password is mandatory") String password,
			String picture, String shippingAddress) {
		super();
		this.userId = userId;
		this.familyName = familyName;
		this.firstName = firstName;
		this.email = email;
		this.username = username;
		this.password = password;
		this.picture = picture;
		this.shippingAddress = shippingAddress;
	}

	public User(List<Orders> orders, String familyName, String firstName,
			@NotNull(message = "Email is mandatory") @NotEmpty(message = "Email is mandatory") @NotBlank(message = "Email is mandatory") String email,
			@NotNull(message = "Username is mandatory") @NotEmpty(message = "Username is mandatory") @NotBlank(message = "Username is mandatory") String username,
			@NotNull(message = "Password is mandatory") @NotEmpty(message = "Password is mandatory") @NotBlank(message = "Password is mandatory") String password,
			Role role, boolean isEnabled, Cart cart, String picture, String shippingAddress) {
		super();
		this.orders = orders;
		this.familyName = familyName;
		this.firstName = firstName;
		this.email = email;
		this.username = username;
		this.password = password;
		this.role = role;
		this.isEnabled = isEnabled;
		this.cart = cart;
		this.picture = picture;
		this.shippingAddress = shippingAddress;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public List<Orders> getOrders() {
		return orders;
	}

	public void setOrders(List<Orders> orders) {
		this.orders = orders;
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Role getRole() {
		return role;
	}

	public boolean isEnabled() {
		return isEnabled;
	}

	public void setEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
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