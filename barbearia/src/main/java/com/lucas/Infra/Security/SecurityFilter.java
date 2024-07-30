package com.lucas.Infra.Security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.lucas.exceptions.InvalidTokenException;
import com.lucas.models.Adminstrador;
import com.lucas.models.Barbeiro;
import com.lucas.repositories.AdministradorRepositorio;
import com.lucas.repositories.BarbeiroRepositorio;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFilter extends OncePerRequestFilter {

	@Autowired
	BarbeiroRepositorio repositorio;

	@Autowired
	AdministradorRepositorio adminRepositorio;

	@Autowired
	TokenServico tokenServico;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String path = request.getRequestURI();
		//System.out.println("Requisição para: " + path);

		// Ignora a validação de token para as rotas de login e registro
		if (path.equals("/auth/login") || path.equals("/auth/registrar")) {
			filterChain.doFilter(request, response);
			return;
		}

		String token = recoverToken(request);
		System.out.println("Token recuperado: " + token);

		if (token != null) {
			try {
				String login = tokenServico.validarToken(token);
				// System.out.println("Login recuperado do token: " + login);

				Adminstrador admin = adminRepositorio.findByEmail(login);
				if (admin != null) {
					// System.out.println("Administrador encontrado: " + admin.getEmail());
					UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(admin,
							null, admin.getAuthorities());
					SecurityContextHolder.getContext().setAuthentication(authentication);
				} else {
					Barbeiro barbeiro = repositorio.findByEmail(login);
					if (barbeiro != null) {
						// System.out.println("Barbeiro encontrado: " + barbeiro.getEmail());
						UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
								barbeiro, null, barbeiro.getAuthorities());
						SecurityContextHolder.getContext().setAuthentication(authentication);
					} else {
						System.out.println("Usuário não encontrado para o login: " + login);
					}
				}
			} catch (InvalidTokenException e) {
				System.out.println("Token inválido: " + e.getMessage());
			}
		} else {
			System.out.println("Cabeçalho de autorização não encontrado ou inválido");
		}

		filterChain.doFilter(request, response);
	}

	private String recoverToken(HttpServletRequest request) {
		String authHeader = request.getHeader("Authorization");
		if (authHeader != null && authHeader.startsWith("Bearer ")) {
			return authHeader.substring(7);
		}
		return null;
	}
}