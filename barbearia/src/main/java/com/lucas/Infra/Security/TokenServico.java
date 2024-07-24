package com.lucas.Infra.Security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/*import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.lucas.exceptions.InvalidTokenException;
import com.lucas.models.Barbeiro;

@Service
public class TokenServico {
	
	
	//@Value("${api.security.token.secret}")
	@Value("${jwt.secret:my-secret-key}")
	private String secret;

	public String gerarToken(Barbeiro barbeiro) {
		try {
			Algorithm algoritimo = Algorithm.HMAC256(secret);
			String token = JWT.create()
					.withIssuer("auth-api") //indica de onde o token esta sendo gerado
					.withSubject(barbeiro.getEmail()) //faz a criptografia com o email junto para que na hora da descrptografia, idenfique o barbeiro
					.withExpiresAt(genExpirationDate()) //tempo para expiracao
					.sign(algoritimo); // faz a assinatura>??
			return token;
		} catch (JWTCreationException exception) {
			throw new RuntimeException("Erro ao gerar token.", exception);
		}
	}
	
	public String validarToken(String token) {
		try {
			Algorithm algoritimo = Algorithm.HMAC256(secret);
			return JWT.require(algoritimo)
					.withIssuer("auth-api")
					.build()
					.verify(token)
					.getSubject();
		}
		 catch (JWTVerificationException exception) {
		        throw new InvalidTokenException("Token inválido ou expirado.");
		    }
	}
	
	private Instant genExpirationDate() {
		return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
	}
}*/

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.lucas.exceptions.InvalidTokenException;
import com.lucas.models.Barbeiro;

@Service
public class TokenServico {

    @Value("${jwt.secret:my-secret-key}")
    private String secret;

    public String gerarToken(Barbeiro barbeiro) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("auth-api")
                    .withSubject(barbeiro.getEmail())
                    .withExpiresAt(genExpirationDate())
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Erro ao gerar token.", exception);
        }
    }

    public String validarToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("auth-api")
                    .build();
            return verifier.verify(token).getSubject();
        } catch (JWTVerificationException exception) {
            throw new InvalidTokenException("Token inválido ou expirado.");
        }
    }

    private Instant genExpirationDate() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}

