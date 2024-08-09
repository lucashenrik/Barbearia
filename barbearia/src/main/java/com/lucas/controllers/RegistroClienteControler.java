package com.lucas.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.lucas.models.Cliente;
import com.lucas.services.RegistroClienteServico;

@RestController
@RequestMapping(value = "/cliente")
public class RegistroClienteControler {
    
    @Autowired
    RegistroClienteServico clienteServico;
    
    @PostMapping(value = "/criar")
    public ResponseEntity<Void> createCliente(@RequestBody Cliente cliente){
        Cliente novoCliente = clienteServico.create(cliente);
        
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
        		.path("/atendimento")
        		.queryParam("clienteId", novoCliente.getId())
        		.build()
        		.toUri();
        return ResponseEntity.created(location).build();
    }
}
