package com.lucas.models.dtos;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;

public class HorariosTrabalhoDTO {
	private Long barbeiroId;
    private List<DayOfWeek> diasTrabalho;
	private LocalTime horaInicio;
	private LocalTime horaFim;
	private LocalTime horaInicioAlmoco;
	private LocalTime horaFimAlmoco;
	
	public Long getBarbeiroId() {
		return barbeiroId;
	}
	public void setBarbeiroId(Long barbeiroId) {
		this.barbeiroId = barbeiroId;
	}
	public List<DayOfWeek> getDiasTrabalho() {
		return diasTrabalho;
	}
	public void setDiasTrabalho(List<DayOfWeek> diasTrabalho) {
		this.diasTrabalho = diasTrabalho;
	}
	public LocalTime getHoraInicio() {
		return horaInicio;
	}
	public void setHoraInicio(LocalTime horaInicio) {
		this.horaInicio = horaInicio;
	}
	public LocalTime getHoraFim() {
		return horaFim;
	}
	public void setHoraFim(LocalTime horaFim) {
		this.horaFim = horaFim;
	}
	public LocalTime getHoraInicioAlmoco() {
		return horaInicioAlmoco;
	}
	public void setHoraInicioAlmoco(LocalTime horaInicioAlmoco) {
		this.horaInicioAlmoco = horaInicioAlmoco;
	}
	public LocalTime getHoraFimAlmoco() {
		return horaFimAlmoco;
	}
	public void setHoraFimAlmoco(LocalTime horaFimAlmoco) {
		this.horaFimAlmoco = horaFimAlmoco;
	}
	
}
