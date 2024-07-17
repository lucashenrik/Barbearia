package com.lucas.models;

import java.util.List;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Administrador {

	@Id
	private Long id;
	
	private String nome;
	private String email;
	private String senha;
	
	@OneToMany(mappedBy= "administrador")
	private List<Barbeiro> barbeiros;

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

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public List<Barbeiro> getBarbeiros() {
		return barbeiros;
	}

	public void setBarbeiros(List<Barbeiro> barbeiros) {
		this.barbeiros = barbeiros;
	}

	@Override
	public int hashCode() {
		return Objects.hash(barbeiros, email, id, nome, senha);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Administrador other = (Administrador) obj;
		return Objects.equals(barbeiros, other.barbeiros) && Objects.equals(email, other.email)
				&& Objects.equals(id, other.id) && Objects.equals(nome, other.nome)
				&& Objects.equals(senha, other.senha);
	}
}
