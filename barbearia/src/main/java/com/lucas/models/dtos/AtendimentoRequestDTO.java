package com.lucas.models.dtos;

import java.time.LocalTime;
import java.util.List;

import com.lucas.models.Servico;

public class AtendimentoRequestDTO {
	private Long clienteId;
	private Long barbeiroId;
	private List<Servico> servicos;
	private LocalTime horario;
	public Long getClienteId() {
		return clienteId;
	}
	public void setClienteId(Long clienteId) {
		this.clienteId = clienteId;
	}
	public Long getBarbeiroId() {
		return barbeiroId;
	}
	public void setBarbeiroId(Long barbeiroId) {
		this.barbeiroId = barbeiroId;
	}
	public List<Servico> getServicos() {
		return servicos;
	}
	public void setServicos(List<Servico> servicos) {
		this.servicos = servicos;
	}
	public LocalTime getHorario() {
		return horario;
	}
	public void setHorario(LocalTime horario) {
		this.horario = horario;
	}

}
