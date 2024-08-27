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

import com.lucas.models.Barbeiro;
import com.lucas.models.dtos.BarbeiroGetDTO;
import com.lucas.services.BarbeiroServico;

@RestController
@RequestMapping("/barbeiros")
@CrossOrigin(origins = "http://localhost:3000")
public class BarbeiroControler {

	@Autowired
	BarbeiroServico barbeiroServico;

	@GetMapping("/buscar")
	public ResponseEntity<List<BarbeiroGetDTO>> findAll() {
		List<BarbeiroGetDTO> barbeiros = barbeiroServico.findAll();
		return new ResponseEntity<>(barbeiros, HttpStatus.OK);
	}

	@PostMapping("/criar")
	public ResponseEntity<Barbeiro> create(@RequestBody Barbeiro barbeiro) {
		barbeiro = barbeiroServico.create(barbeiro);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@PutMapping("/atualizar")
	public ResponseEntity<Void> update(@RequestBody Barbeiro barbeiro) {
		barbeiro = barbeiroServico.update(barbeiro);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@DeleteMapping("/deletar")
	public ResponseEntity<Void> delete(@RequestParam Long id) {
		barbeiroServico.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
