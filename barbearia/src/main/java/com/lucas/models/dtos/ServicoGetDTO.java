package com.lucas.models.dtos;

import java.time.Duration;

public class ServicoGetDTO {
	
	private String nomeServico;
	private Double preco;
	private Long duracaoMinutos;
	private Duration duracao;
	private String nomeBarbeiro;
	private String telefone;
	private String email;
	public String getNomeServico() {
		return nomeServico;
	}
	public void setNomeServico(String nomeServico) {
		this.nomeServico = nomeServico;
	}
	public Double getPreco() {
		return preco;
	}
	public void setPreco(Double preco) {
		this.preco = preco;
	}
	public Long getDuracaoMinutos() {
		return duracaoMinutos;
	}
	public void setDuracaoMinutos(Long duracaoMinutos) {
		this.duracaoMinutos = duracaoMinutos;
	}
	public Duration getDuracao() {
		return duracao;
	}
	public void setDuracao(Duration duracao) {
		this.duracao = duracao;
	}
	public String getNomeBarbeiro() {
		return nomeBarbeiro;
	}
	public void setNomeBarbeiro(String nomeBarbeiro) {
		this.nomeBarbeiro = nomeBarbeiro;
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
}
