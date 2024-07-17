package com.lucas.models;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_barbeiro")
public class Barbeiro implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String nome;
	
	@Column(unique = true)
	private String email;
	
	@Column(nullable = false, length = 11)
	private String telefone;
	
	@Column(nullable = false)
	private String senha;
	
	@OneToMany(mappedBy = "barbeiro")
	@JsonIgnore
	private List<Servico> servicos;
	
	@OneToMany(mappedBy = "barbeiro")
	@JsonIgnore
	private List<Atendimentos> atendimentos;
	
    @OneToMany(mappedBy = "barbeiro", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<HorariosTrabalho> horariosTrabalho = new HashSet<>();
    
    @ManyToOne
    @JoinColumn(name = "administrador_id")
    @JsonIgnore
    private Administrador administrador;

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

	public List<Servico> getServicos() {
		return servicos;
	}

	public void setServicos(List<Servico> servicos) {
		this.servicos = servicos;
	}

	public List<Atendimentos> getAtendimentos() {
		return atendimentos;
	}

	public void setAtendimentos(List<Atendimentos> atendimentos) {
		this.atendimentos = atendimentos;
	}

	public Set<HorariosTrabalho> getHorariosTrabalho() {
		return horariosTrabalho;
	}

	public void setHorariosTrabalho(Set<HorariosTrabalho> horariosTrabalho) {
		this.horariosTrabalho = horariosTrabalho;
	}

	public Administrador getAdministrador() {
		return administrador;
	}

	public void setAdministrador(Administrador administrador) {
		this.administrador = administrador;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		return Objects.hash(administrador, atendimentos, email, horariosTrabalho, id, nome, senha, servicos, telefone);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Barbeiro other = (Barbeiro) obj;
		return Objects.equals(administrador, other.administrador) && Objects.equals(atendimentos, other.atendimentos)
				&& Objects.equals(email, other.email) && Objects.equals(horariosTrabalho, other.horariosTrabalho)
				&& Objects.equals(id, other.id) && Objects.equals(nome, other.nome)
				&& Objects.equals(senha, other.senha) && Objects.equals(servicos, other.servicos)
				&& Objects.equals(telefone, other.telefone);
	}
}