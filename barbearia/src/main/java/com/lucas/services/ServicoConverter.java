package com.lucas.services;

import com.lucas.models.Servico;
import com.lucas.models.dtos.ServicoRequestDTO;

public class ServicoConverter {
	
	public static ServicoRequestDTO toDTO(Servico servico){
		ServicoRequestDTO dto = new ServicoRequestDTO();
		dto.setBarbeiroId(servico.getId());
		dto.setNomeServico(servico.getNomeServico());
		dto.setPreco(servico.getPreco());
		return dto;
	}
}
