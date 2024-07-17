package com.lucas.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lucas.models.Servico;

@Repository
public interface ServicoRepositorio extends JpaRepository<Servico, Long>{
	List<Servico> findByBarbeiroId(Long barbeiroId);
}
