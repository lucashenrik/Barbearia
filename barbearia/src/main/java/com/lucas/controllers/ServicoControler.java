package com.lucas.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lucas.models.dtos.DuracaoEnum;
import com.lucas.models.dtos.ServicoGetDTO;
import com.lucas.models.dtos.ServicoRequestDTO;
import com.lucas.repositories.BarbeiroRepositorio;
import com.lucas.services.ServicoServico;

@RestController
@RequestMapping(value = "/servicos")
public class ServicoControler {

	@Autowired
	ServicoServico servicoServico;
	
	@Autowired
	BarbeiroRepositorio barbeiroRepositorio;
	
	//ServicoGetDTO servicoGetDTO;
	
	@GetMapping(value = "/buscar")
	public ResponseEntity<List<ServicoGetDTO>> findAll(){
		List<ServicoGetDTO> servicos = servicoServico.findAll();
		return new ResponseEntity<>(servicos, HttpStatus.OK);
	}
	
	@GetMapping(value = "/buscarPorIdBarbeiro")
	public ResponseEntity<List<ServicoGetDTO>> findByIdBarbeiroId(@RequestParam Long barbeiroId){
		List<ServicoGetDTO> servicos = servicoServico.findByIdBarbeiroId(barbeiroId);
		return new ResponseEntity<>(servicos, HttpStatus.OK);
	}
	
	/*@PostMapping(value = "/criar")
	public ResponseEntity<ServicoGetDTO> create(@RequestBody ServicoRequestDTO servicoRequestDTO){
		ServicoGetDTO servicoDTO = servicoServico.converterServicoParaDTO(servicoRequestDTO);
		
		return new ResponseEntity<>(servicoDTO, HttpStatus.CREATED);
	}*/
	
	@PostMapping(value = "/criar")
	public ResponseEntity<Void> create(@RequestBody ServicoRequestDTO servicoRequestDTO){
		servicoServico.create(servicoRequestDTO);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	/*@PutMapping(value = "/atualizar")
	public ResponseEntity<Void> atualizar(@RequestBody ServicoRequestDTO servicoRequestDTO){
		servicoServico.update(servicoRequestDTO);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}*/
	
	@DeleteMapping(value = "/deletar")
	public ResponseEntity<Void> deletar(@RequestParam Long id){
		servicoServico.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/durations")
    public ResponseEntity<List<String>> getPossibleDurations() {
        List<String> durations = servicoServico.getPossibleDurations().stream()
                                .map(DuracaoEnum::getDescricao)
                                .collect(Collectors.toList());
        return ResponseEntity.ok(durations);
    }
}
