package com.lucas.controllers;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lucas.services.HorariosServico;

@RestController
@RequestMapping(value = "/barbeiros")
public class HorariosControler {

	@Autowired
	HorariosServico horariosServico;
	
	/*@GetMapping("/{barbeiroId}/horarios-disponiveis")
    public List<LocalTime> getHorariosDisponiveis(
            @PathVariable Long barbeiroId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data,
            @RequestParam int duracaoServicoEmMinutos) {

        Duration duracaoServico = Duration.ofMinutes(duracaoServicoEmMinutos);
        return horariosServico.getHorariosDisponiveis(barbeiroId, data, duracaoServico);
    }*/
	
	   @GetMapping("/{barbeiroId}/horarios-disponiveis")
	    public ResponseEntity<List<LocalTime>> getHorariosDisponiveis(
	            @PathVariable Long barbeiroId,
	            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDateTime data,
	            @RequestParam int duracaoServicoEmMinutos) {

	        Duration duracaoServico = Duration.ofMinutes(duracaoServicoEmMinutos);
	        List<LocalTime> horariosDisponiveis = horariosServico.getHorariosDisponiveis(barbeiroId, data, duracaoServico);
	        return ResponseEntity.ok(horariosDisponiveis);
	   }
}
