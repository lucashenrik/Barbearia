package com.lucas.services;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.lucas.models.Barbeiro;
import com.lucas.models.HorariosTrabalho;
import com.lucas.models.dtos.HorariosTrabalhoDTO;
import com.lucas.repositories.BarbeiroRepositorio;
import com.lucas.repositories.HorariosTrabalhoRepositorio;

import jakarta.persistence.EntityNotFoundException;

@Service
public class HorariosServico {
    
    @Autowired
    private BarbeiroRepositorio barbeiroRepositorio;

    @Autowired
    private HorariosTrabalhoRepositorio horariosTrabalhoRepositorio;
    
    @Autowired
    AtendimentoServico atendServico;
    
    public List<HorariosTrabalho> getByBarbeiroId(Long barbeiroId) {
        return horariosTrabalhoRepositorio.findByBarbeiroId(barbeiroId);
    }
    
    public List<LocalTime> getHorariosDisponiveisForBarbeiros(Long barbeiroId, LocalDateTime data) {
        DayOfWeek diaDaSemana = data.getDayOfWeek();
        List<HorariosTrabalho> horariosTrabalho = horariosTrabalhoRepositorio.findByBarbeiroIdAndDiaDaSemana(barbeiroId, diaDaSemana);

        System.out.println("Horarios de trabalho encontrados: " + horariosTrabalho.size()); // Log para depuração

        List<LocalTime> horariosDisponiveis = new ArrayList<>();

        for (HorariosTrabalho horario : horariosTrabalho) {
            LocalTime horaAtual = horario.getHoraInicio();
            LocalTime horaFim = horario.getHoraFim();
            LocalTime horaInicioAlmoco = horario.getHoraInicioAlmoco();
            LocalTime horaFimAlmoco = horario.getHoraFimAlmoco();


                    if (!atendServico.horarioEstaOcupado(barbeiroId, data, horaAtual)) {
                        horariosDisponiveis.add(horaAtual);
                    }
        }

        return horariosDisponiveis;
    }
    
    public List<LocalTime> getHorariosDisponiveis(Long barbeiroId, LocalDateTime data, Duration duracaoServico) {
        DayOfWeek diaDaSemana = data.getDayOfWeek();
        List<HorariosTrabalho> horariosTrabalho = horariosTrabalhoRepositorio.findByBarbeiroIdAndDiaDaSemana(barbeiroId, diaDaSemana);

        System.out.println("Horarios de trabalho encontrados: " + horariosTrabalho.size()); // Log para depuração

        List<LocalTime> horariosDisponiveis = new ArrayList<>();

        for (HorariosTrabalho horario : horariosTrabalho) {
            LocalTime horaAtual = horario.getHoraInicio();
            LocalTime horaFim = horario.getHoraFim();
            LocalTime horaInicioAlmoco = horario.getHoraInicioAlmoco();
            LocalTime horaFimAlmoco = horario.getHoraFimAlmoco();

            while (horaAtual.plus(duracaoServico).isBefore(horaFim) || horaAtual.plus(duracaoServico).equals(horaFim)) {
                boolean duranteAlmoco = horaInicioAlmoco != null && horaFimAlmoco != null &&
                        (horaAtual.isAfter(horaInicioAlmoco) || horaAtual.equals(horaInicioAlmoco)) &&
                        (horaAtual.plus(duracaoServico).isBefore(horaFimAlmoco) || horaAtual.plus(duracaoServico).equals(horaFimAlmoco));

                if (!duranteAlmoco) {
                    if (!atendServico.horarioEstaOcupado(barbeiroId, data, horaAtual)) {
                        horariosDisponiveis.add(horaAtual);
                    }
                }

                horaAtual = horaAtual.plus(duracaoServico);
            }
        }

        System.out.println("Horarios disponíveis: " + horariosDisponiveis); // Log para depuração

        return horariosDisponiveis;
    }
    
    public void criarHorariosTrabalho(HorariosTrabalhoDTO dto) {
        Barbeiro barbeiro = barbeiroRepositorio.findById(dto.getBarbeiroId())
                .orElseThrow(() -> new RuntimeException("Barbeiro não encontrado"));

        for (DayOfWeek dia : dto.getDiasTrabalho()) {
            HorariosTrabalho horario = new HorariosTrabalho();
            horario.setBarbeiro(barbeiro);
            horario.setDiaDaSemana(dia);
            horario.setHoraInicio(dto.getHoraInicio());
            horario.setHoraFim(dto.getHoraFim());
            horario.setHoraInicioAlmoco(dto.getHoraInicioAlmoco());
            horario.setHoraFimAlmoco(dto.getHoraFimAlmoco());

            horariosTrabalhoRepositorio.save(horario);
        }
    }
    
    public void atualizarHorarioTrabalho(Long barbeiroId, LocalDate data, HorariosTrabalho horariosTrabalho) {
       
        /// Verificar se o barbeiro existe
        Barbeiro barbeiro = barbeiroRepositorio.findById(barbeiroId).orElseThrow(() -> new EntityNotFoundException("Barbeiro não encontrado"));

        // Obter o dia da semana a partir da data fornecida
        DayOfWeek diaDaSemana = data.getDayOfWeek();

        // Buscar os horários de trabalho do barbeiro específico para o dia da semana
        List<HorariosTrabalho> horariosExistentes = horariosTrabalhoRepositorio.findByBarbeiroIdAndDiaDaSemana(barbeiroId, diaDaSemana);
        if (horariosExistentes.isEmpty()) {
            throw new EntityNotFoundException("Horários de trabalho não encontrados para o barbeiro no dia especificado");
        }
        
        // Atualizar os horários de trabalho encontrados
        horariosExistentes.stream()
                .map(horarioExistente -> {
                    horarioExistente.setHoraInicio(horariosTrabalho.getHoraInicio());
                    horarioExistente.setHoraFim(horariosTrabalho.getHoraFim());
                    horarioExistente.setHoraInicioAlmoco(horariosTrabalho.getHoraInicioAlmoco());
                    horarioExistente.setHoraFimAlmoco(horariosTrabalho.getHoraFimAlmoco());
                    return horariosTrabalhoRepositorio.save(horarioExistente);
                }).collect(Collectors.toList());

    }
    
}
