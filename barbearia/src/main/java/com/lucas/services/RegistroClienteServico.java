package com.lucas.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucas.models.Cliente;
import com.lucas.repositories.ClienteRepositorio;

@Service
public class RegistroClienteServico {

	@Autowired
	ClienteRepositorio clienteRepositorio;
	
	
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
}
