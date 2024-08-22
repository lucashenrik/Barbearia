package com.lucas.controllers;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lucas.models.Barbeiro;
import com.lucas.models.HorariosTrabalho;
import com.lucas.models.dtos.HorariosTrabalhoDTO;
import com.lucas.repositories.BarbeiroRepositorio;
import com.lucas.repositories.HorariosTrabalhoRepositorio;
import com.lucas.services.HorariosServico;

import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/horarios")
public class HorariosTrabalhoControler {

    @Autowired
    private HorariosTrabalhoRepositorio horariosTrabalhoRepositorio;
    
    @Autowired
    private HorariosServico horariosServico;
    
    @Autowired
    private BarbeiroRepositorio barbeiroRepo;
   
    //Chama a funcao que retorna todos os dias da semana.
    /*@GetMapping
    public List<String> listarDiasSemana() {
        return horariosServico.listarDiasSemana();
    }*/

    @GetMapping("/barbeiro/{barbeiroId}")
    public ResponseEntity<List<HorariosTrabalho>> getByBarbeiroId(@PathVariable Long barbeiroId) {
        List<HorariosTrabalho> horarios = horariosServico.getByBarbeiroId(barbeiroId);
        return ResponseEntity.ok(horarios);
    }
    
    @PostMapping("/criar-horario")
    public ResponseEntity<Void> definirHorarioTrabalho(@RequestBody HorariosTrabalhoDTO horariosTrabalhoDTO) {
    	horariosServico.criarHorariosTrabalho(horariosTrabalhoDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{barbeiroId}/atualizar")
    public ResponseEntity<Void> atualizarHorarioTrabalho(
    		@PathVariable Long barbeiroId,
            @RequestParam(name = "data") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data,
            @RequestBody HorariosTrabalho horariosTrabalho) {
    	
    	try {
            horariosServico.atualizarHorarioTrabalho(barbeiroId, data, horariosTrabalho);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
    

    @DeleteMapping("/{barbeiroId}/deletar")
    @Transactional
    public ResponseEntity<Void> delete(
    		@PathVariable Long barbeiroId,
    		@RequestParam(name = "data") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data) {
        
    	DayOfWeek diaDaSemana = data.getDayOfWeek();
    	 
    	List<HorariosTrabalho> horariosNoDia = horariosTrabalhoRepositorio.findByDiaDaSemana(diaDaSemana);
    	
    	// Verificar se a lista é nula ou está vazia
        if (horariosNoDia == null || horariosNoDia.isEmpty()) {
            return ResponseEntity.badRequest().body(null);
        }
    	
        // Deletar os horários de trabalho encontrados
        horariosTrabalhoRepositorio.deleteByDiaDaSemanaAndBarbeiroId(diaDaSemana, barbeiroId);
        
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
