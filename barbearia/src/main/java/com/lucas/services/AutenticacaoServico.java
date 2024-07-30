package com.lucas.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.lucas.Infra.Security.TokenServico;
import com.lucas.models.AdminUserDetails;
import com.lucas.models.Adminstrador;
import com.lucas.models.Barbearia;
import com.lucas.models.Barbeiro;
import com.lucas.models.CustomUserDetails;
import com.lucas.models.EnumRoles;
import com.lucas.models.dtos.AutenticacaoDTO;
import com.lucas.models.dtos.RegistrarDTO;
import com.lucas.repositories.AdministradorRepositorio;
import com.lucas.repositories.BarbeiroRepositorio;

@Service
public class AutenticacaoServico {

	@Autowired
	AdministradorRepositorio adminRepositorio;

	@Autowired
	BarbeiroRepositorio barbRepositorio;
	
	@Autowired
	TokenServico tokenServico;
	
	@Autowired
	private AuthenticationManager authenticationManager;

	public void registrar(RegistrarDTO data) {

		// Codifica a senha
		String senhaEncriptada = new BCryptPasswordEncoder().encode(data.senha());

		// Cria a barbearia, se necessário
		Barbearia barbearia = null; // Defina isso conforme necessário

		EnumRoles roleRequest = data.role();

		if (roleRequest == EnumRoles.ADMIN) {
			Adminstrador admin = new Adminstrador(data.nome(), data.email(), data.telefone(), senhaEncriptada,
					data.role(), barbearia);
			this.adminRepositorio.save(admin);
		} else if (roleRequest == EnumRoles.BARBEIRO) {
			Barbeiro novoBarbeiro = new Barbeiro(data.nome(), data.email(), data.telefone(), senhaEncriptada,
					data.role(), null, barbearia);
			this.barbRepositorio.save(novoBarbeiro);
		}
	}
	
	public String login(AutenticacaoDTO login) {
		// Verifique se a senha está presente
        if (login.senha() == null || login.senha().isEmpty()) {
            throw new BadCredentialsException("Senha não pode estar vazia");
        }

        // Autenticação
        var usernamePassword = new UsernamePasswordAuthenticationToken(login.email(), login.senha());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        Object principal = auth.getPrincipal();
        String token;

        // Geração de token baseado no tipo de usuário
        if (principal instanceof AdminUserDetails) {
            AdminUserDetails userDetails = (AdminUserDetails) principal;
            Adminstrador admin = userDetails.getAdmin();
            System.out.println("Administrador autenticado: " + admin.getEmail());
            token = tokenServico.AdminGerarToken(admin);
        } else if (principal instanceof CustomUserDetails) {
            CustomUserDetails userDetails = (CustomUserDetails) principal;
            Barbeiro barbeiro = userDetails.getBarbeiro();
            System.out.println("Barbeiro autenticado: " + barbeiro.getEmail());
            token = tokenServico.BarbGerarToken(barbeiro);
        } else {
            throw new RuntimeException("Tipo de usuário não suportado: " + principal.getClass().getName());
        }

        SecurityContextHolder.getContext().setAuthentication(auth);
        
        return token;
	}
}
