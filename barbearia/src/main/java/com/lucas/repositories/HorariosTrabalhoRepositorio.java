
package com.lucas.repositories;

import java.time.DayOfWeek;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.lucas.models.HorariosTrabalho;

public interface HorariosTrabalhoRepositorio extends JpaRepository<HorariosTrabalho, Long> {
    List<HorariosTrabalho> findByDiaDaSemana(DayOfWeek diaDaSemana);
    List<HorariosTrabalho> findByBarbeiroId(Long barbeiroId);
    List<HorariosTrabalho> findByBarbeiroIdAndDiaDaSemana(Long barbeiroId, DayOfWeek diaDaSemana);
    
    @Transactional
    void deleteByDiaDaSemanaAndBarbeiroId(DayOfWeek diaDaSemana, Long barbeiroId);
}

