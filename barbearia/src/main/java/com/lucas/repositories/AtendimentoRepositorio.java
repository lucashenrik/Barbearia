package com.lucas.repositories;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lucas.models.Atendimentos;

@Repository
public interface AtendimentoRepositorio extends JpaRepository<Atendimentos, Long>{	
	  List<Atendimentos> findByClienteId(Long clienteId);
	  List<Atendimentos> findByBarbeiroId(Long barbeiroId);
	  boolean existsByBarbeiroIdAndDataAndHorario(Long barbeiroId, LocalDateTime data, LocalTime horario);
}
