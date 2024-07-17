package com.lucas.models;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_cliente")
public class Cliente implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "nome_cliente", nullable = false, length = 80)
	private String nome;
	
	@Column(name = "email_cliente", nullable = false)
	private String email;
	
	@Column(name = "telefone_cliente", nullable = false, length = 11)
	private String telefone;
	
	@OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY)
	private List<Atendimentos> atendimentos;
	
	public Long getId() {
		return id;
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

	public void setTelefone(String numero) {
		this.telefone = numero;
	}

	public List<Atendimentos> getAtendimento() {
		return atendimentos;
	}

	public void setAtendimento(List<Atendimentos> proxCorte) {
		this.atendimentos = proxCorte;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		return Objects.hash(email, id, nome, telefone, atendimentos);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		return Objects.equals(email, other.email) && Objects.equals(id, other.id) && Objects.equals(nome, other.nome)
				&& Objects.equals(telefone, other.telefone) && Objects.equals(atendimentos, other.atendimentos);
	}
}
