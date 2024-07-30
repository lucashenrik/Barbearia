package com.lucas.models;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class CustomUserDetails implements UserDetails {

	private static final long serialVersionUID = 1L;
	//private static final long serialVersionUID = -3232298329147722752L;
	
	private Barbeiro barbeiro;

    public CustomUserDetails(Barbeiro barbeiro) {
        this.barbeiro = barbeiro;
    }

    public Barbeiro getBarbeiro() {
        return barbeiro;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(() -> barbeiro.getRole().name());
    }

    @Override
    public String getPassword() {
        return barbeiro.getSenha();
    }

    @Override
    public String getUsername() {
        return barbeiro.getEmail();
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
