package com.lucas.services;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucas.exceptions.ResourceNotFoundException;
import com.lucas.models.Barbeiro;
import com.lucas.models.Servico;
import com.lucas.models.dtos.BarbeiroGetDTO;
import com.lucas.models.dtos.DuracaoEnum;
import com.lucas.models.dtos.ServicoGetDTO;
import com.lucas.models.dtos.ServicoRequestDTO;
import com.lucas.repositories.BarbeiroRepositorio;

@Service
public class ConverterServico {
	
	@Autowired
	BarbeiroRepositorio barbeiroRepositorio;
	
	@Autowired
	BarbeiroServico barbeiroServico;

	public ServicoGetDTO convertToDTO(Servico servico) {
		ServicoGetDTO servicoDTO = new ServicoGetDTO();
		servicoDTO.setNomeServico(servico.getNomeServico());
		servicoDTO.setPreco(servico.getPreco());
		Duration duracao = servico.getDuracao();
		long duracaoMinutos = duracao.toMinutes();
		servicoDTO.setDuracaoMinutos(duracaoMinutos);
		servicoDTO.setNomeBarbeiro(servico.getBarbeiro().getNome());
		servicoDTO.setEmail(servico.getBarbeiro().getEmail());
		servicoDTO.setTelefone(servico.getBarbeiro().getTelefone());
		
		return servicoDTO;
	}
	
	public ServicoGetDTO converterServicoParaDTO(ServicoRequestDTO servicoRequestDTO) {
		BarbeiroGetDTO barbeiroDTO = barbeiroServico.converterBarbeiroParaDTO(servicoRequestDTO.getBarbeiroId());
		
		ServicoGetDTO servicoDTO = new ServicoGetDTO();
		servicoDTO.setNomeServico(servicoRequestDTO.getNomeServico());
		servicoDTO.setPreco(servicoRequestDTO.getPreco());
		Duration duracao = convertDuracaoEnumToDuration(servicoRequestDTO.getDuracao());
		servicoDTO.setDuracao(duracao);
		servicoDTO.setNomeBarbeiro(barbeiroDTO.getNomeBarbeiro());
		servicoDTO.setTelefone(barbeiroDTO.getTelefone());
		servicoDTO.setEmail(barbeiroDTO.getEmail());
		 
		return servicoDTO;
	}
	
	public Servico converterRequestToServico(ServicoRequestDTO servicoRequestDTO) {
		Barbeiro barbeiro = barbeiroRepositorio.findById(servicoRequestDTO.getBarbeiroId())
                .orElseThrow(() -> new ResourceNotFoundException("Barbeiro não encontrado."));
		Servico servico = new Servico();
		servico.setNomeServico(servicoRequestDTO.getNomeServico());
		servico.setPreco(servicoRequestDTO.getPreco());
		Duration duracao = convertDuracaoEnumToDuration(servicoRequestDTO.getDuracao());
		servico.setDuracao(duracao);
		servico.setBarbeiro(barbeiro);
        return servico;
	}
	
	public Duration convertDuracaoEnumToDuration(DuracaoEnum duracaoEnum) {
	    switch (duracaoEnum) {
	        case PT10M:
	            return Duration.ofMinutes(10);
	        case PT15M:
	            return Duration.ofMinutes(15);
	        case PT20M:
	            return Duration.ofMinutes(20);
	        case PT30M:
	            return Duration.ofMinutes(30);
	        case PT40M:
	            return Duration.ofMinutes(40);
	        case PT1H:
	            return Duration.ofHours(1);
	        case PT1H30M:
	            return Duration.ofHours(1).plusMinutes(30);
	        case PT2H:
	            return Duration.ofHours(2);
	        case PT2H30M:
	            return Duration.ofHours(2).plusMinutes(30);
	        case PT3H:
	            return Duration.ofHours(3);
	        case PT3H30M:
	            return Duration.ofHours(3).plusMinutes(30);
	        case PT4H:
	            return Duration.ofHours(4);
	        case PT4H30M:
	            return Duration.ofHours(4).plusMinutes(30);
	        default:
	            throw new IllegalArgumentException("Duração não reconhecida: " + duracaoEnum);
	    }
	}
}