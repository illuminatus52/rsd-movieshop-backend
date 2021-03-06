package com.rsd_movieshop.security;

import com.rsd_movieshop.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class CustomUserDetails implements UserDetails {
	
	private final User user;
	private final boolean isEnabled;
	
	
	public CustomUserDetails(User user, boolean isEnabled) {
		super();
		this.user = user;
		this.isEnabled = isEnabled;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<SimpleGrantedAuthority> authorities = new LinkedList<>();
		authorities.add(new SimpleGrantedAuthority(this.user.getRole().toString()));
		return authorities;
	}
	
	@Override
	public String getPassword() {
		return user.getPassword();
	}
	
	@Override
	public String getUsername() {
		return user.getUsername();
	}
	
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	
	@Override
	public boolean isEnabled() {
		return isEnabled;
	}
}