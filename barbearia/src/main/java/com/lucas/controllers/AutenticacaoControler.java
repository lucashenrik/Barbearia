package com.lucas.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lucas.models.dtos.AutenticacaoDTO;
import com.lucas.models.dtos.LoginResponseDTO;
import com.lucas.models.dtos.RegistrarDTO;
import com.lucas.services.AutenticacaoServico;

@RestController
@RequestMapping("auth")
public class AutenticacaoControler {

	@Autowired
	AutenticacaoServico authServico;

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody AutenticacaoDTO login) {
		try {
			String token = authServico.login(login);
			return ResponseEntity.ok(new LoginResponseDTO(token));

		} catch (BadCredentialsException e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
					.body("Usuário inexistente ou senha inválida: " + e.getMessage());
		}
	}

	@PostMapping("/registrar")
	public ResponseEntity<?> registrar(@RequestBody @Validated RegistrarDTO data) {
		try {
			authServico.registrar(data);
			return ResponseEntity.ok().build();

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao registrar novo barbeiro");
		}
	}
}
