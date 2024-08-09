package com.lucas.services;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lucas.exceptions.ResourceNotFoundException;
import com.lucas.models.Atendimentos;
import com.lucas.models.Barbeiro;
import com.lucas.models.Cliente;
import com.lucas.models.HorariosTrabalho;
import com.lucas.models.Servico;
import com.lucas.models.dtos.AtendimentoGetDTO;
import com.lucas.models.dtos.AtendimentoRequestDTO;
import com.lucas.models.dtos.BarbeiroGetDTO;
import com.lucas.models.dtos.ServicoGetDTO;
import com.lucas.repositories.AtendimentoRepositorio;
import com.lucas.repositories.BarbeiroRepositorio;
import com.lucas.repositories.ClienteRepositorio;
import com.lucas.repositories.HorariosTrabalhoRepositorio;
import com.lucas.repositories.ServicoRepositorio;

@Service
public class AtendimentoServico {

	@Autowired
	AtendimentoRepositorio atendRepositorio;
	
	@Autowired
	ClienteRepositorio clienteRepositorio;
	
	@Autowired
	ServicoRepositorio servicoRepositorio;
	
	@Autowired
	BarbeiroRepositorio barbeiroRepositorio;
	
	@Autowired
    private HorariosTrabalhoRepositorio horariosTrabalhoRepositorio;
	
	@Autowired
	ConverterServico converterServico;
	
	
    @Transactional(readOnly = true)
	public List<AtendimentoGetDTO> findAtendimentosByClienteId(Long clienteId) {
        List<Atendimentos> atendimentos = atendRepositorio.findByClienteId(clienteId);
        
        // Inicializa explicitamente a coleção de serviços para evitar LazyInitializationException
        atendimentos.forEach(atendimento -> {
            atendimento.getServicos().size();
        });
        
        return atendimentos.stream()
                .map(AtendimentoConverter::toDTO)
                .collect(Collectors.toList());
    }
    
    @Transactional(readOnly = true)
	public List<AtendimentoGetDTO> findAtendimentosByBarbeiroId(Long barbeiroId) {
        List<Atendimentos> atendimentos = atendRepositorio.findByClienteId(barbeiroId);
        
        // Inicializa explicitamente a coleção de serviços para evitar LazyInitializationException
        atendimentos.forEach(atendimento -> {
            atendimento.getServicos().size();
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
	
	public List<BarbeiroGetDTO> findAllBarbeiros() {
		List<Barbeiro> barbeiros = barbeiroRepositorio.findAll();
		
		List<BarbeiroGetDTO> barbeiroDTOs = barbeiros.stream()
				.map(barbeiro -> {
					BarbeiroGetDTO barbeiroDTO = new BarbeiroGetDTO();
					barbeiroDTO.setNomeBarbeiro(barbeiro.getNome());
					barbeiroDTO.setEmail(barbeiro.getEmail());
					barbeiroDTO.setTelefone(barbeiro.getTelefone());
					return barbeiroDTO;
				})
				.collect(Collectors.toList());
		
		return barbeiroDTOs;
	}
	
	public List<ServicoGetDTO> findServicoByIdBarbeiroId(Long barbeiroId) {
		List<Servico> servicos = servicoRepositorio.findByBarbeiroId(barbeiroId);
		 return servicos.stream().map(converterServico::convertToDTO).collect(Collectors.toList());
	}

	 public List<LocalTime> getHorariosDisponiveis(Long barbeiroId, LocalDateTime data, Duration duracaoServico) {
	        DayOfWeek diaDaSemana = data.getDayOfWeek();
	        List<HorariosTrabalho> horariosTrabalho = horariosTrabalhoRepositorio.findByBarbeiroIdAndDiaDaSemana(barbeiroId, diaDaSemana);

	        System.out.println("Horarios de trabalho encontrados: " + horariosTrabalho.size()); // Log para depuração

	        List<LocalTime> horariosDisponiveis = new ArrayList<>();

	        for (HorariosTrabalho horario : horariosTrabalho) {
	            LocalTime horaAtual = horario.getHoraInicio();
	            LocalTime horaFim = horario.getHoraFim();
	            LocalTime horaInicioAlmoco = horario.getHoraInicioAlmoco();
	            LocalTime horaFimAlmoco = horario.getHoraFimAlmoco();

	            while (horaAtual.plus(duracaoServico).isBefore(horaFim) || horaAtual.plus(duracaoServico).equals(horaFim)) {
	                boolean duranteAlmoco = horaInicioAlmoco != null && horaFimAlmoco != null &&
	                        (horaAtual.isAfter(horaInicioAlmoco) || horaAtual.equals(horaInicioAlmoco)) &&
	                        (horaAtual.plus(duracaoServico).isBefore(horaFimAlmoco) || horaAtual.plus(duracaoServico).equals(horaFimAlmoco));

	                if (!duranteAlmoco) {
	                    if (!horarioEstaOcupado(barbeiroId, data, horaAtual)) {
	                        horariosDisponiveis.add(horaAtual);
	                    }
	                }

	                horaAtual = horaAtual.plus(duracaoServico);
	            }
	        }

	        System.out.println("Horarios disponíveis: " + horariosDisponiveis); // Log para depuração

	        return horariosDisponiveis;
	    }
}