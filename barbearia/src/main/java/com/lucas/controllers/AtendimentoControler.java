package com.lucas.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lucas.models.Atendimentos;
import com.lucas.models.dtos.AtendimentoGetDTO;
import com.lucas.models.dtos.AtendimentoRequestDTO;
import com.lucas.models.dtos.BarbeiroGetDTO;
import com.lucas.models.dtos.ServicoGetDTO;
import com.lucas.services.AtendimentoConverter;
import com.lucas.services.AtendimentoServico;

@RestController
@RequestMapping("/atendimento")
@CrossOrigin(origins = "http://localhost:3000")
public class AtendimentoControler {

	@Autowired
	AtendimentoServico atendimentoServico;
	
	@GetMapping//("/buscar")
	public ResponseEntity<List<AtendimentoGetDTO>> findAtendimentos(@RequestParam Long clienteId){
		List<AtendimentoGetDTO> atendimentosDTO = atendimentoServico.findAtendimentosByClienteId(clienteId);
		
		return new ResponseEntity<>(atendimentosDTO, HttpStatus.OK);
	}
	
	@PostMapping("/criar-atendimento")
	public ResponseEntity<AtendimentoGetDTO> create( @RequestBody AtendimentoRequestDTO atendimentoRequestDTO){
		Atendimentos novoAtendimento = atendimentoServico.create(
				atendimentoRequestDTO.getClienteId(),
				atendimentoRequestDTO.getBarbeiroId(),
				atendimentoRequestDTO.getServicos(),
				atendimentoRequestDTO.getHorario());
		
		AtendimentoGetDTO atendimentoDTO = AtendimentoConverter.toDTO(novoAtendimento);
		
		return new ResponseEntity<>(atendimentoDTO, HttpStatus.CREATED);
	}
	
	@PutMapping("/atualizar")
	public ResponseEntity<Void> update(@RequestBody AtendimentoRequestDTO atendimentoRequestDTO){
		atendimentoServico.update(atendimentoRequestDTO);
		
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@DeleteMapping("/cancelar")
	public ResponseEntity<Void> delete(@RequestParam Long id){
		atendimentoServico.delete(id);
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/barbeiros-disponiveis")
	public ResponseEntity<List<BarbeiroGetDTO>> buscarBarbeiros(){
		List<BarbeiroGetDTO> barbeiros = atendimentoServico.findAllBarbeiros();
		return new ResponseEntity<>(barbeiros, HttpStatus.OK);
	}
	
	@GetMapping("/buscarPorIdBarbeiro")
	public ResponseEntity<List<ServicoGetDTO>> findServicoByIdBarbeiroId(@RequestParam Long barbeiroId){
		List<ServicoGetDTO> servicos = atendimentoServico.findServicoByIdBarbeiroId(barbeiroId);
		return new ResponseEntity<>(servicos, HttpStatus.OK);
	}
}
