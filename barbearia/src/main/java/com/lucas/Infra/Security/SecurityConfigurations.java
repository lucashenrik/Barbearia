package com.lucas.Infra.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.lucas.models.EnumRoles;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations {
	
	@Autowired
	SecurityFilter securityFilter;
	
	//@Autowired
	//private AuthorizationService userDetailsService;
	
	@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity, UserDetailsService userDetailsService) throws Exception {
        return httpSecurity
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                	    .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                	    .requestMatchers(HttpMethod.POST, "/auth/registrar").permitAll()
                	    .requestMatchers(HttpMethod.GET, "/barbeiros").hasAuthority(EnumRoles.BARBEIRO.getRole())
                	    .requestMatchers(HttpMethod.POST, "/barbeiros").hasAuthority(EnumRoles.BARBEIRO.getRole())
                	    .requestMatchers(HttpMethod.PUT, "/barbeiros").hasAuthority(EnumRoles.BARBEIRO.getRole())
                	    .requestMatchers(HttpMethod.DELETE, "/barbeiros").hasAuthority(EnumRoles.BARBEIRO.getRole())
                	    .anyRequest().authenticated()
                	)
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .userDetailsService(userDetailsService)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	    
}
