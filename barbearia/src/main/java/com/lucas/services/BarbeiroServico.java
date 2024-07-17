package com.lucas.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucas.exceptions.ResourceNotFoundException;
import com.lucas.models.Barbeiro;
import com.lucas.models.dtos.BarbeiroGetDTO;
import com.lucas.repositories.BarbeiroRepositorio;

@Service
public class BarbeiroServico {
	
	@Autowired
	BarbeiroRepositorio barbeiroRepositorio;
	
	public Barbeiro findById(Long id) {
		Barbeiro barbeiro = barbeiroRepositorio.findById(id).get();
		return barbeiro;
	}
	
	public List<BarbeiroGetDTO> findAll() {
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
	
	public BarbeiroGetDTO converterBarbeiroParaDTO(Long barbeiroId) {
		Barbeiro barbeiro = barbeiroRepositorio.findById(barbeiroId)
				 .orElseThrow(() -> new ResourceNotFoundException("Barbeiro não encontrado com ID: " + barbeiroId));
		
		BarbeiroGetDTO barbeiroDTO = new BarbeiroGetDTO();
		barbeiroDTO.setNomeBarbeiro(barbeiro.getNome());
		barbeiroDTO.setTelefone(barbeiro.getTelefone());
		barbeiroDTO.setEmail(barbeiro.getEmail());
		
		return barbeiroDTO;
	}
	
	public Barbeiro create(Barbeiro barbeiro) {
		barbeiro = barbeiroRepositorio.save(barbeiro);
		return barbeiro;
	}
	
	public Barbeiro update(Barbeiro barbeiro) {
		Barbeiro barbeiroNovo = barbeiroRepositorio.findById(barbeiro.getId()).get();
		barbeiroNovo.setEmail(barbeiro.getEmail());
		barbeiroNovo.setNome(barbeiro.getNome());
		barbeiroNovo.setSenha(barbeiro.getSenha());	
		return barbeiroRepositorio.save(barbeiroNovo);
	}
	
	public void delete(Long id) {
		var barbeiroTemp = barbeiroRepositorio.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Não foi possível encontrar um barbeiro com este id."));
		barbeiroRepositorio.delete(barbeiroTemp);
	}

}
