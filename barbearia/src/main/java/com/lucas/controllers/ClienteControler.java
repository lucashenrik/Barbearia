package com.lucas.controllers;

import java.net.URI;
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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.lucas.models.Cliente;
import com.lucas.models.Servico;
import com.lucas.models.dtos.ClienteGetDTO;
import com.lucas.services.ClienteServico;

@RestController
@RequestMapping("/cliente")
@CrossOrigin(origins = "http://localhost:3000")
public class ClienteControler {
    
    @Autowired
    ClienteServico clienteServico;
    
    @GetMapping("/buscar")
    public ResponseEntity<List<ClienteGetDTO>> buscarClientes(){
    	List<ClienteGetDTO> clientesDTO = clienteServico.findAll();
    	
    	return new ResponseEntity<>(clientesDTO, HttpStatus.OK);
    }
    
    @PostMapping("/criar")
    public ResponseEntity<String> createCliente(@RequestBody Cliente cliente) {
        Cliente novoCliente = clienteServico.create(cliente);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/atendimento")
                .queryParam("clienteId", novoCliente.getId())
                .build()
                .toUri();

        return ResponseEntity.created(location).body(novoCliente.getId().toString());
    }
    
    @PutMapping("/atualizar")
    public ResponseEntity<Void> updateCliente(@RequestBody Cliente cliente){
    	clienteServico.update(cliente);
    	
    	return new ResponseEntity<>(HttpStatus.CREATED);
    }
    
    @DeleteMapping("/deletar")
    public ResponseEntity<Void> delete(@RequestParam Long clienteId){
    	clienteServico.delete(clienteId);
    	
    	return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    // Endpoint para selecionar serviço
    @GetMapping("/selecionarServico")
    public ResponseEntity<List<Servico>> selecionarServico(@RequestParam Long clienteId) {
        // Lógica para buscar serviços personalizados para o cliente, se necessário.
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
}
