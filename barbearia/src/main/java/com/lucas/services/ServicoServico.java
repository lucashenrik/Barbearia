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
	
	@Autowired
	ConverterServico converterServico;
	
	public List<ServicoGetDTO> findAll() {
		List<Servico> servicos = servicoRepositorio.findAll();
		return servicos.stream().map(converterServico::convertToDTO).collect(Collectors.toList());
	}
	
	public List<ServicoGetDTO> findByIdBarbeiroId(Long barbeiroId) {
		List<Servico> servicos = servicoRepositorio.findByBarbeiroId(barbeiroId);
		 return servicos.stream().map(converterServico::convertToDTO).collect(Collectors.toList());
	}
	
	ServicoGetDTO servicoDTO;
	
	public Servico create(ServicoRequestDTO servicoRequestDTO) {
		Barbeiro barbeiro = barbeiroRepositorio.findById(servicoRequestDTO.getBarbeiroId())
                .orElseThrow(() -> new ResourceNotFoundException("Barbeiro não encontrado."));
		Servico servico = new Servico();
		servico.setNomeServico(servicoRequestDTO.getNomeServico());
		servico.setPreco(servicoRequestDTO.getPreco());
	    Duration duracao = converterServico.convertDuracaoEnumToDuration(servicoRequestDTO.getDuracao());
	    servico.setDuracao(duracao);
		servico.setBarbeiro(barbeiro);
        return servicoRepositorio.save(servico);
	}
	
	public void delete(Long id) {
		var servico = servicoRepositorio.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Não foi possível encontrar um servico com este id."));
		servicoRepositorio.delete(servico);
	}
	
	public List<DuracaoEnum> getPossibleDurations() {
        return Arrays.asList(DuracaoEnum.values());
    }
}