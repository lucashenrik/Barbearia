package com.lucas.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.lucas.models.AdminUserDetails;
import com.lucas.models.Adminstrador;
import com.lucas.models.Barbeiro;
import com.lucas.models.CustomUserDetails;
import com.lucas.repositories.AdministradorRepositorio;
import com.lucas.repositories.BarbeiroRepositorio;

@Service
public class AuthorizationService implements UserDetailsService {

	@Autowired
	BarbeiroRepositorio barbeiroRepositorio;

	@Autowired
	AdministradorRepositorio adminRepositorio;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		// Primeiro, tente encontrar um administrador pelo email
		Adminstrador adminOpt = adminRepositorio.findByEmail(email);
		if (adminOpt != null) {
			System.out.println("Administrador encontrado: " + adminOpt.getEmail() + ", senha: " + adminOpt.getSenha());
			return new AdminUserDetails(adminOpt);
		}

		// Se não encontrou um administrador, tente encontrar um barbeiro
		Barbeiro barbeiroOpt = barbeiroRepositorio.findByEmail(email);
		if (barbeiroOpt != null) {
			System.out.println("Barbeiro encontrado: " + barbeiroOpt.getEmail() + ", senha: " + barbeiroOpt.getSenha());
			return new CustomUserDetails(barbeiroOpt);
		}

		// Se nenhum usuário for encontrado, lance uma exceção apropriada
		throw new UsernameNotFoundException("User not found with email: " + email);
	}
}