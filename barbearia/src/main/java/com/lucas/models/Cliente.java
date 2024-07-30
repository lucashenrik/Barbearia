package com.lucas.models;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
	
	@ManyToOne
    @JoinColumn(name = "barbearia_id")
	@JsonBackReference
    private Barbearia barbearia;

	public Cliente() {
	}

	public Cliente(String nome, Long id, String email, String telefone, List<Atendimentos> atendimentos,
			Barbearia barbearia) {
		super();
		this.nome = nome;
		this.id = id;
		this.email = email;
		this.telefone = telefone;
		this.atendimentos = atendimentos;
		this.barbearia = barbearia;
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

	public List<Atendimentos> getAtendimentos() {
		return atendimentos;
	}

	public void setAtendimentos(List<Atendimentos> atendimentos) {
		this.atendimentos = atendimentos;
	}

	public Barbearia getBarbearia() {
		return barbearia;
	}

	public void setBarbearia(Barbearia barbearia) {
		this.barbearia = barbearia;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		return Objects.hash(atendimentos, barbearia, email, id, nome, telefone);
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
		return Objects.equals(atendimentos, other.atendimentos) && Objects.equals(barbearia, other.barbearia)
				&& Objects.equals(email, other.email) && Objects.equals(id, other.id)
				&& Objects.equals(nome, other.nome) && Objects.equals(telefone, other.telefone);
	}
}
