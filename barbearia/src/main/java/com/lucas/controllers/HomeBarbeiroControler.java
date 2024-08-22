package com.lucas.controllers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lucas.models.HorariosTrabalho;
import com.lucas.models.dtos.AtendimentoGetDTO;
import com.lucas.models.dtos.HorariosTrabalhoDTO;
import com.lucas.models.dtos.ServicoGetDTO;
import com.lucas.models.dtos.ServicoRequestDTO;
import com.lucas.services.AtendimentoServico;
import com.lucas.services.HorariosServico;
import com.lucas.services.ServicoServico;

@RestController
@RequestMapping("/home")
public class HomeBarbeiroControler {
	
	@Autowired
	AtendimentoServico atendServico;
	
	@Autowired
	HorariosServico horarioServico;
	
	@Autowired
	ServicoServico servicoServico;
	
	@GetMapping("/atendimentos")
	public ResponseEntity<List<AtendimentoGetDTO>> findAtendimentos(@RequestParam Long barbeiroId){
		List<AtendimentoGetDTO> atendimentos = atendServico.findAtendimentosByBarbeiroId(barbeiroId);
		
		return new ResponseEntity<>(atendimentos, HttpStatus.OK);
	}
	
	@GetMapping("/horarios")
	public ResponseEntity<List<LocalTime>> horariosDisponiveis(@PathVariable Long barbeiroId, @RequestParam LocalDateTime data){
		List<LocalTime> horariosDisponiveis = horarioServico.getHorariosDisponiveisForBarbeiros(barbeiroId, data);
		
		return new ResponseEntity<>(horariosDisponiveis, HttpStatus.OK);
	}
	
	@PostMapping("/novo-horario")
	public ResponseEntity<HorariosTrabalhoDTO> novoHorario(@RequestBody HorariosTrabalhoDTO data){
		horarioServico.criarHorariosTrabalho(data);
		
		return new ResponseEntity<>(HttpStatus.CREATED);
		}
	
	@PutMapping("/atualizar-horario")
	public ResponseEntity<Void> atualizarHorario(@PathVariable Long barbeiroId, @RequestParam LocalDate data, @RequestBody HorariosTrabalho horariosTrabalho){
		horarioServico.atualizarHorarioTrabalho(barbeiroId, data, horariosTrabalho);
		
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@GetMapping("/servicos")
	public ResponseEntity<List<ServicoGetDTO>> meusServicos(@RequestParam Long barbeiroId){
		List<ServicoGetDTO> servicos = servicoServico.findByIdBarbeiroId(barbeiroId);
		
		return new ResponseEntity<>(servicos, HttpStatus.OK);
	}
	
	@PostMapping("/servicos/criar")
	public ResponseEntity<Void> criarServico(@RequestBody ServicoRequestDTO servicoDTO){
		servicoServico.create(servicoDTO);
		
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@DeleteMapping("/servicos/excluir")
	public ResponseEntity<Void> excluirServico(@RequestBody Long servicoID){
		servicoServico.delete(servicoID);
		
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
