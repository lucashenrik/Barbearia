package com.lucas.models.dtos;

public class ServicoRequestDTO {

	private String nomeServico;
	private Double preco;
	private DuracaoEnum duracao;
	private Long barbeiroId;
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
	public DuracaoEnum getDuracao() {
		return duracao;
	}
	public void setDuracao(DuracaoEnum duracao) {
		this.duracao = duracao;
	}
	public Long getBarbeiroId() {
		return barbeiroId;
	}
	public void setBarbeiroId(Long barbeiroId) {
		this.barbeiroId = barbeiroId;
	}
}
