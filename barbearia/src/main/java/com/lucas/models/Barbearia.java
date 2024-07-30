package com.lucas.models;

import java.util.Objects;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "barbearia")
public class Barbearia {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "nome_barbearia", nullable = false)
    private String nome;
    
    @Column
    private String endereco;
    
    @Column
    private String telefone;
    
    @Column
    private String email;
    
    @OneToMany(mappedBy = "barbearia", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private Set<Adminstrador> administrador;

    @OneToMany(mappedBy = "barbearia", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private Set<Barbeiro> barbeiros;
    
    @OneToMany(mappedBy = "barbearia", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private Set<Cliente> clientes;
    
    public Barbearia() {
    }
    
    public Barbearia(Long id, String nome, String endereco, String telefone, String email,
            Set<Adminstrador> administrador, Set<Barbeiro> barbeiros, Set<Cliente> clientes) {
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
        this.email = email;
        this.administrador = administrador;
        this.barbeiros = barbeiros;
        this.clientes = clientes;
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

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<Adminstrador> getAdministrador() {
		return administrador;
	}

	public void setAdministrador(Set<Adminstrador> administrador) {
		this.administrador = administrador;
	}

	public Set<Barbeiro> getBarbeiros() {
		return barbeiros;
	}

	public void setBarbeiros(Set<Barbeiro> barbeiros) {
		this.barbeiros = barbeiros;
	}

	public Set<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(Set<Cliente> clientes) {
		this.clientes = clientes;
	}

	@Override
	public int hashCode() {
		return Objects.hash(administrador, barbeiros, clientes, email, endereco, id, nome, telefone);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Barbearia other = (Barbearia) obj;
		return Objects.equals(administrador, other.administrador) && Objects.equals(barbeiros, other.barbeiros)
				&& Objects.equals(clientes, other.clientes) && Objects.equals(email, other.email)
				&& Objects.equals(endereco, other.endereco) && Objects.equals(id, other.id)
				&& Objects.equals(nome, other.nome) && Objects.equals(telefone, other.telefone);
	}
}
