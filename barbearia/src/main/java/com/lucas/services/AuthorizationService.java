package com.lucas.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.lucas.models.Barbeiro;
import com.lucas.models.CustomUserDetails;
import com.lucas.repositories.BarbeiroRepositorio;

@Service
public class AuthorizationService implements UserDetailsService {

	@Autowired
	BarbeiroRepositorio barbeiroRepositorio;
	
	@Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Barbeiro barbeiro = barbeiroRepositorio.findByEmail(email);
        
        //System.out.println("Barbeiro encontrado: " + barbeiro.getEmail() + " senha: " + barbeiro.getSenha());

        if (barbeiro == null) {
            throw new UsernameNotFoundException("User not found");
        }
        /*return User.builder()
                   .username(barbeiro.getEmail())
                   .password(barbeiro.getSenha())
                   .roles(barbeiro.getRole().name())
                   .build();*/
        return new CustomUserDetails(barbeiro);
    }
        
}
