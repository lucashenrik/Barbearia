package com.lucas.services;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lucas.exceptions.ResourceNotFoundException;
import com.lucas.models.Atendimentos;
import com.lucas.models.Barbeiro;
import com.lucas.models.Cliente;
import com.lucas.models.Servico;
import com.lucas.models.dtos.AtendimentoGetDTO;
import com.lucas.models.dtos.AtendimentoRequestDTO;
import com.lucas.repositories.AtendimentoRepositorio;
import com.lucas.repositories.BarbeiroRepositorio;
import com.lucas.repositories.ClienteRepositorio;
import com.lucas.repositories.ServicoRepositorio;

@Service
public class AtendimentoServico {

	@Autowired
	AtendimentoRepositorio atendRepositorio;
	
	@Autowired
	ClienteRepositorio clienteRepositorio;
	
	@Autowired
	BarbeiroRepositorio barbeiroRepositorio;
	
	@Autowired
	ServicoRepositorio servicoRepositorio;
	
    @Transactional(readOnly = true)
	public List<AtendimentoGetDTO> findAtendimentosByClienteId(Long clienteId) {
        List<Atendimentos> atendimentos = atendRepositorio.findByClienteId(clienteId);
        
        // Inicializa explicitamente a coleção de serviços para evitar LazyInitializationException
        atendimentos.forEach(atendimento -> {
            atendimento.getServicos().size(); // ou Hibernate.initialize(atendimento.getServicos());
        });
        
        return atendimentos.stream()
                .map(AtendimentoConverter::toDTO)
                .collect(Collectors.toList());
    }
	
	public Atendimentos create(Long clienteId, Long barbeiroId, List<Servico> servicoRequestDTO, LocalTime horario) {
		Cliente cliente = clienteRepositorio.findById(clienteId)
				.orElseThrow(() -> new ResourceNotFoundException("Não foi possível encontrar um usuario com este id."));
		Barbeiro barbeiro = barbeiroRepositorio.findById(barbeiroId)
				.orElseThrow(() -> new ResourceNotFoundException("Não foi possível encontrar um barbeiro com este id."));
		
		List<Servico> servicos = servicoRequestDTO.stream()
                .map(dto -> servicoRepositorio.findById(dto.getId()).orElseThrow(() -> new ResourceNotFoundException("Serviço não encontrado com ID: " + dto.getId())))
                .collect(Collectors.toList());
		   
		Atendimentos atendimento = new Atendimentos();
		atendimento.setCliente(cliente);
		atendimento.setBarbeiro(barbeiro);
		atendimento.setServicos(servicos);
		atendimento.setData(LocalDateTime.now());
		atendimento.setHorario(horario);
		
		return atendRepositorio.save(atendimento);
	}
	
	public Atendimentos update(AtendimentoRequestDTO atendimento) {
		Atendimentos atendimentoTemp = atendRepositorio.findById(atendimento.getClienteId()).get();
		atendimentoTemp.setServicos(atendimento.getServicos());
		atendimentoTemp.setData(LocalDateTime.now());
		return atendRepositorio.save(atendimentoTemp);
	}
	
	public void delete(Long id) {
		var atendiTemp = atendRepositorio.findById(id)
			.orElseThrow(() -> new ResourceNotFoundException("Não foi possível encontrar um usuario com este id."));
		atendRepositorio.delete(atendiTemp);
	}
	
	public boolean horarioEstaOcupado(Long barbeiroId, LocalDateTime data, LocalTime horario) {
        //return atendRepositorio.existsByBarbeiroIdAndDataAndHorario(barbeiroId, data, horario);
		boolean ocupado = atendRepositorio.existsByBarbeiroIdAndDataAndHorario(barbeiroId, data, horario);
		System.out.println("Horário " + horario + " em " + data + " está ocupado: " + ocupado); // Log para depuração
		return ocupado;
    }

}
