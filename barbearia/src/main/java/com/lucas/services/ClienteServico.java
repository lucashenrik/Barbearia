package com.lucas.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucas.exceptions.ResourceNotFoundException;
import com.lucas.models.Cliente;
import com.lucas.models.dtos.ClienteGetDTO;
import com.lucas.repositories.ClienteRepositorio;

@Service
public class ClienteServico {

	@Autowired
	ClienteRepositorio clienteRepositorio;
	
	public List<ClienteGetDTO> findAll() {
		List<Cliente> clientes = clienteRepositorio.findAll();
	
		List<ClienteGetDTO> clientesDTO = clientes.stream()
	            .map(this::converterToDTO)
	            .collect(Collectors.toList());
	    return clientesDTO;
	}
	
	public Cliente create(Cliente cliente) {
		cliente = clienteRepositorio.save(cliente);
		return cliente;
	}
	
	public Cliente update(Cliente cliente) {
		Cliente clienteTemp = clienteRepositorio.findById(cliente.getId()).get();
		clienteTemp.setTelefone(cliente.getTelefone());
		clienteTemp.setEmail(cliente.getEmail());
		return clienteRepositorio.save(clienteTemp);
	}
	
	public void delete(Long id) {
		var clienteTmp = clienteRepositorio.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Não foi possível encontrar um cliente com este id."));
		clienteRepositorio.delete(clienteTmp);
	}
	
	public ClienteGetDTO converterToDTO(Cliente cliente) {
		ClienteGetDTO clienteDTO = new ClienteGetDTO();
		clienteDTO.setNomeCliente(cliente.getNome());
		clienteDTO.setEmailCliente(cliente.getEmail());
		clienteDTO.setTelefoneCliente(cliente.getTelefone());
		
		return clienteDTO;
	}
	
	
}
