package com.lucas.models.dtos;

import java.util.List;

public class AtendimentoGetDTO {
	private String nomeCliente;
	private String nomeBarbeiro;
	private List<ServicoRequestDTO> servicos;
	private Double precoTotalServicos;
	
	public String getNomeCliente() {
		return nomeCliente;
	}
	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}
	public String getNomeBarbeiro() {
		return nomeBarbeiro;
	}
	public void setNomeBarbeiro(String nomeBarbeiro) {
		this.nomeBarbeiro = nomeBarbeiro;
	}
	public List<ServicoRequestDTO> getServicos() {
		return servicos;
	}
	public void setServicos(List<ServicoRequestDTO> servicos) {
		this.servicos = servicos;
	}
	public Double getPrecoTotalServicos() {
		return precoTotalServicos;
	}
	public void setPrecoTotalServicos(Double precoTotalServicos) {
		this.precoTotalServicos = precoTotalServicos;
	}
	
	
}
