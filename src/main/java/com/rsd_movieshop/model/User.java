package com.rsd_movieshop.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

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

	@OneToMany(mappedBy = "userid")
	private List<Orders> orders = new ArrayList<Orders>();

	private String familyName;
	private String firstName;
	private String email;

	@Column(nullable = false, unique = true)
	private String username;

	@JsonIgnore
	@Column(nullable = false)
	private String password;
	@Column(nullable = false)
	private String role = "ROLE_USER";

	@OneToOne(cascade = { CascadeType.PERSIST, CascadeType.REMOVE })
	@JoinColumn(name = "cart")
	private Cart cart = new Cart();

	public User() {
		super();
	}

	public User(long userId, String familyName, String firstName, String email, String username, String password) {
		super();
		this.userId = userId;
		this.familyName = familyName;
		this.firstName = firstName;
		this.email = email;
		this.username = username;
		this.password = password;
	}

	public User(String familyName, String firstName, String email, String username, String password, String role) {
		super();
		this.familyName = familyName;
		this.firstName = firstName;
		this.email = email;
		this.username = username;
		this.password = password;
		this.role = role;
	}

	public User(List<Orders> orders, String familyName, String firstName, String email, String username,
			String password, String role) {
		super();
		this.orders = orders;
		this.familyName = familyName;
		this.firstName = firstName;
		this.email = email;
		this.username = username;
		this.password = password;
		this.role = role;
	}

	public User(List<Orders> orders, String familyName, String firstName, String email, String username,
			String password, String role, Cart cart) {
		super();
		this.orders = orders;
		this.familyName = familyName;
		this.firstName = firstName;
		this.email = email;
		this.username = username;
		this.password = password;
		this.role = role;
		this.cart = cart;
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

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}
}