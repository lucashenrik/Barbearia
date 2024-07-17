package com.lucas.services;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
import com.lucas.repositories.ServicoRepositorio;

@Service
public class ServicoServico {

	@Autowired
	ServicoRepositorio servicoRepositorio;
	
	@Autowired
	BarbeiroRepositorio barbeiroRepositorio;
	
	@Autowired
	BarbeiroServico barbeiroServico;
	
	public List<ServicoGetDTO> findAll() {
		List<Servico> servicos = servicoRepositorio.findAll();
		return servicos.stream().map(this::convertToDTO).collect(Collectors.toList());
	}
	
	public List<ServicoGetDTO> findByIdBarbeiroId(Long barbeiroId) {
		List<Servico> servicos = servicoRepositorio.findByBarbeiroId(barbeiroId);
		 return servicos.stream().map(this::convertToDTO).collect(Collectors.toList());
	}
	
	ServicoGetDTO servicoDTO;
	
	public Servico create(ServicoRequestDTO servicoRequestDTO) {
		Barbeiro barbeiro = barbeiroRepositorio.findById(servicoRequestDTO.getBarbeiroId())
                .orElseThrow(() -> new ResourceNotFoundException("Barbeiro não encontrado."));
		Servico servico = new Servico();
		servico.setNomeServico(servicoRequestDTO.getNomeServico());
		servico.setPreco(servicoRequestDTO.getPreco());
		 // Convertendo a enumeração para Duration
	    Duration duracao = convertDuracaoEnumToDuration(servicoRequestDTO.getDuracao());
	    servico.setDuracao(duracao);
		servico.setBarbeiro(barbeiro);
        return servicoRepositorio.save(servico);
	}
	
	//Preciso add o id em requestDTO
	/*public Servico update(ServicoRequestDTO servicoRequestDTO) {
	    // Carrega o Servico existente do banco de dados pelo ID
	    Servico servicoExistente = servicoRepositorio.findById(servicoRequestDTO.getId())
	            .orElseThrow(() -> new ResourceNotFoundException("Serviço não encontrado com o ID: " + servicoRequestDTO.getId()));

	    // Atualiza o Servico existente com os novos dados
	    servicoExistente.setNomeServico(servicoRequestDTO.getNomeServico());
	    servicoExistente.setPreco(servicoRequestDTO.getPreco());
	    Duration duracao = convertDuracaoEnumToDuration(servicoRequestDTO.getDuracao());
	    servicoExistente.setDuracao(duracao);

	    // Atualiza o barbeiro apenas se o ID do barbeiro tiver mudado
	    if (servicoExistente.getBarbeiro().getId() != servicoRequestDTO.getBarbeiroId()) {
	        Barbeiro barbeiro = barbeiroRepositorio.findById(servicoRequestDTO.getBarbeiroId())
	                .orElseThrow(() -> new ResourceNotFoundException("Barbeiro não encontrado com o ID: " + servicoRequestDTO.getBarbeiroId()));
	        servicoExistente.setBarbeiro(barbeiro);
	    }

	    // Salva o Servico atualizado de volta no banco de dados
	    return servicoRepositorio.save(servicoExistente);
	}*/
	
	public void delete(Long id) {
		var servico = servicoRepositorio.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Não foi possível encontrar um servico com este id."));
		servicoRepositorio.delete(servico);
	}
	
	public List<DuracaoEnum> getPossibleDurations() {
        return Arrays.asList(DuracaoEnum.values());
    }
	
	private Duration convertDuracaoEnumToDuration(DuracaoEnum duracaoEnum) {
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
	
	private ServicoGetDTO convertToDTO(Servico servico) {
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
	
	private Servico converterRequestToServico(ServicoRequestDTO servicoRequestDTO) {
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
}
