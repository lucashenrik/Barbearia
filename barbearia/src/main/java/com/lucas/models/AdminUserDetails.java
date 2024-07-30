package com.lucas.models;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class AdminUserDetails implements UserDetails {

	private static final long serialVersionUID = 1L;

	private Adminstrador admin;

	public AdminUserDetails(Adminstrador admin) {
		this.admin = admin;
	}

	public Adminstrador getAdmin() {
		return admin;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.singletonList(() -> admin.getRole().name());
	}

	@Override
	public String getPassword() {
		return admin.getSenha();
	}

	@Override
	public String getUsername() {
		return admin.getEmail();
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
		return true;
	}

}
