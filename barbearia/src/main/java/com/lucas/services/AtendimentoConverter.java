package com.lucas.services;

import java.util.stream.Collectors;

import com.lucas.models.Atendimentos;
import com.lucas.models.dtos.AtendimentoGetDTO;

public class AtendimentoConverter {

	public static AtendimentoGetDTO toDTO(Atendimentos atendimento) {
		AtendimentoGetDTO dto = new AtendimentoGetDTO();
		dto.setNomeCliente(atendimento.getCliente().getNome());
		dto.setNomeBarbeiro(atendimento.getBarbeiro().getNome());
		dto.setServicos(atendimento.getServicos()
				.stream().map(ServicoConverter::toDTO)
				.collect(Collectors.toList()));
		
		double precoTotalServicos = atendimento.getServicos().stream()
                .mapToDouble(servico -> servico.getPreco())
                .sum();
		
		dto.setPrecoTotalServicos(precoTotalServicos);
		
		return dto;
	}
	
}
