package com.lucas.models;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoderTest {

    public static void main(String[] args) {
        // Crie uma instância do BCryptPasswordEncoder
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        
        // Defina a senha em texto claro
        String rawPassword = "01234567891";
        
        // Codifique a senha em texto claro
        String encodedPassword = encoder.encode(rawPassword);
        
        // Verifique se a senha original corresponde à senha codificada
        boolean matches = encoder.matches(rawPassword, encodedPassword);
        
        // Exiba os resultados
        System.out.println("Senha original: " + rawPassword);
        System.out.println("Senha codificada: " + encodedPassword);
        System.out.println("Senha corresponde: " + matches);
    }
}