package com.lucas.models;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "administrador")
public class Adminstrador implements UserDetails {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private String nome;

	@Column
	private String email;

	@Column
	private String telefone;

	@Column
	private String senha;

	@Enumerated(EnumType.STRING)
	@Column
	private EnumRoles role;

	@ManyToOne
	@JoinColumn(name = "barbearia_id")
	@JsonBackReference
	private Barbearia barbearia;

	@OneToMany(mappedBy = "administrador")
	private List<Barbeiro> barbeiros;

	public Adminstrador(String nome, String email, String telefone, String senha, EnumRoles role, Barbearia barbearia) {
		this.nome = nome;
		this.email = email;
		this.telefone = telefone;
		this.senha = senha;
		this.role = role;
		this.barbearia = barbearia;
	}

	public Adminstrador() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Barbearia getBarbearia() {
		return barbearia;
	}

	public void setBarbearia(Barbearia barbearia) {
		this.barbearia = barbearia;
	}

	public List<Barbeiro> getBarbeiro() {
		return barbeiros;
	}

	public void setBarbeiro(List<Barbeiro> barbeiro) {
		this.barbeiros = barbeiro;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public EnumRoles getRole() {
		return role;
	}

	public void setRole(EnumRoles role) {
		this.role = role;
	}

	@Override
	public int hashCode() {
		return Objects.hash(barbearia, barbeiros, email, id, nome, role, senha, telefone);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Adminstrador other = (Adminstrador) obj;
		return Objects.equals(barbearia, other.barbearia) && Objects.equals(barbeiros, other.barbeiros)
				&& Objects.equals(email, other.email) && Objects.equals(id, other.id)
				&& Objects.equals(nome, other.nome) && role == other.role && Objects.equals(senha, other.senha)
				&& Objects.equals(telefone, other.telefone);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		if (this.role == EnumRoles.ADMIN)
			return List.of(new SimpleGrantedAuthority("ADMIN"), new SimpleGrantedAuthority("BARBEIRO"));
		else
			return List.of(new SimpleGrantedAuthority("CLIENTE"));
	}

	@Override
	public String getPassword() {
		return null;
	}

	@Override
	public String getUsername() {
		return email;
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