package com.lucas.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lucas.Infra.Security.TokenServico;
import com.lucas.models.Barbeiro;
import com.lucas.models.CustomUserDetails;
import com.lucas.models.dtos.AutenticacaoDTO;
import com.lucas.models.dtos.LoginResponseDTO;
import com.lucas.models.dtos.RegistrarDTO;
import com.lucas.repositories.BarbeiroRepositorio;


@RestController
@RequestMapping("auth")
public class AutenticacaoControler {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private BarbeiroRepositorio repositorio;
	
	@Autowired
	private TokenServico tokenServico;
	
	
	/*@PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Validated AutenticacaoDTO data) {
        try {
            var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.senha());
            var auth = this.authenticationManager.authenticate(usernamePassword);
            //System.out.println("Tentativa de login com email: " + data.email() + " e senha: " + data.senha());

            
            var token = tokenServico.gerarToken((Barbeiro)auth.getPrincipal());
            
            return ResponseEntity.ok(new LoginResponseDTO(token));
            
        } catch (AuthenticationException e) {
            System.out.println("Erro de autenticação: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciais inválidas");
        }
    }*/
	
	
	@PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AutenticacaoDTO data) {
        try {
            Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                		data.email(), data.senha()));

            //var userDetails = (Barbeiro) auth.getPrincipal();
            //var token = tokenServico.gerarToken(userDetails);

            CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();
            Barbeiro barbeiro = userDetails.getBarbeiro();
            
            String token = tokenServico.gerarToken(barbeiro);
            
            SecurityContextHolder.getContext().setAuthentication(auth);
            
            return ResponseEntity.ok(new LoginResponseDTO(token));
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário inexistente ou senha inválida");
        }
    }
	
	
	@PostMapping("/registrar")
	public ResponseEntity registrar(@RequestBody @Validated RegistrarDTO data) {
		if (this.repositorio.findByEmail(data.email()) != null) return ResponseEntity.badRequest().build();
		
		String senhaEncriptada = new BCryptPasswordEncoder().encode(data.senha());
		Barbeiro novoBarbeiro = new Barbeiro(data.nome(), data.email(), data.telefone(), senhaEncriptada, data.role());
		
		System.out.println("nome do barbeiro eh: " + data.nome());
		this.repositorio.save(novoBarbeiro);
		
		return ResponseEntity.ok().build();
	}
}
