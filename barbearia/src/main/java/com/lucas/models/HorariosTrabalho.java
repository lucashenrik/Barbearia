

/*package com.lucas.models;

import java.time.LocalTime;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_horarios_trabalho")
public class HorariosTrabalho {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @OneToMany(mappedBy = "horarioTrabalho", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<DiasTrabalho> diasTrabalho;
    
    @Column(name = "hora_inicio", nullable = false)
    private LocalTime horaInicio;
    
    @Column(name = "hora_fim", nullable = false)
    private LocalTime horaFim;
    
    @Column(name = "hora_inicio_almoco", nullable = true)
    private LocalTime horaInicioAlmoco;
    
    @Column(name = "hora_fim_almoco", nullable = true)
    private LocalTime horaFimAlmoco;
    
    @ManyToOne
    @JoinColumn(name = "barbeiro_id", nullable = false)
    private Barbeiro barbeiro;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

	public Set<DiasTrabalho> getDiasTrabalho() {
		return diasTrabalho;
	}

	public void setDiasTrabalho(Set<DiasTrabalho> diasTrabalho) {
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

    public Barbeiro getBarbeiro() {
        return barbeiro;
    }

    public void setBarbeiro(Barbeiro barbeiro) {
        this.barbeiro = barbeiro;
    }

    public void addDiasTrabalho(DiasTrabalho dia) {
        diasTrabalho.add(dia);
        dia.setHorarioTrabalho(this); // Associando o horário de trabalho a este dia de trabalho
    }
    
	@Override
	public int hashCode() {
		return Objects.hash(barbeiro, diasTrabalho, horaFim, horaFimAlmoco, horaInicio, horaInicioAlmoco, id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HorariosTrabalho other = (HorariosTrabalho) obj;
		return Objects.equals(barbeiro, other.barbeiro) && Objects.equals(diasTrabalho, other.diasTrabalho)
				&& Objects.equals(horaFim, other.horaFim) && Objects.equals(horaFimAlmoco, other.horaFimAlmoco)
				&& Objects.equals(horaInicio, other.horaInicio)
				&& Objects.equals(horaInicioAlmoco, other.horaInicioAlmoco) && Objects.equals(id, other.id);
	}
}*/

/*package com.lucas.models;

import java.time.LocalTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_horarios_trabalho")
public class HorariosTrabalho {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
   
    @OneToMany(mappedBy = "horarioTrabalho", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<DiasTrabalho> diasTrabalho = new HashSet<>();
    
    @Column(name = "hora_inicio", nullable = false)
    private LocalTime horaInicio;
    
    @Column(name = "hora_fim", nullable = false)
    private LocalTime horaFim;
    
    @Column(name = "hora_inicio_almoco", nullable = true)
    private LocalTime horaInicioAlmoco;
    
    @Column(name = "hora_fim_almoco", nullable = true)
    private LocalTime horaFimAlmoco;
    
    @ManyToOne
    @JoinColumn(name = "barbeiro_id", nullable = false)
    private Barbeiro barbeiro;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<DiasTrabalho> getDiasTrabalho() {
        return diasTrabalho;
    }

    public void setDiasTrabalho(Set<DiasTrabalho> diasTrabalho) {
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

    public Barbeiro getBarbeiro() {
        return barbeiro;
    }

    public void setBarbeiro(Barbeiro barbeiro) {
        this.barbeiro = barbeiro;
    }

    // Método para adicionar DiasTrabalho ao Set
    public void addDiasTrabalho(DiasTrabalho dia) {
        this.diasTrabalho.add(dia);
        dia.setHorarioTrabalho(this); // Associando o horário de trabalho a este dia de trabalho
    }

    @Override
    public int hashCode() {
        return Objects.hash(barbeiro, diasTrabalho, horaFim, horaFimAlmoco, horaInicio, horaInicioAlmoco, id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        HorariosTrabalho other = (HorariosTrabalho) obj;
        return Objects.equals(barbeiro, other.barbeiro) && Objects.equals(diasTrabalho, other.diasTrabalho)
                && Objects.equals(horaFim, other.horaFim) && Objects.equals(horaFimAlmoco, other.horaFimAlmoco)
                && Objects.equals(horaInicio, other.horaInicio)
                && Objects.equals(horaInicioAlmoco, other.horaInicioAlmoco) && Objects.equals(id, other.id);
    }
}*/

package com.lucas.models;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_horarios_trabalho")
public class HorariosTrabalho {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "dia_da_semana", nullable = false)
    private DayOfWeek diaDaSemana;

    @Column(name = "hora_inicio", nullable = false)
    private LocalTime horaInicio;

    @Column(name = "hora_fim", nullable = false)
    private LocalTime horaFim;

    @Column(name = "hora_inicio_almoco", nullable = true)
    private LocalTime horaInicioAlmoco;

    @Column(name = "hora_fim_almoco", nullable = true)
    private LocalTime horaFimAlmoco;

    @ManyToOne
    @JoinColumn(name = "barbeiro_id", nullable = false)
    private Barbeiro barbeiro;
    
    @Column(name = "dias_de_trabalho", nullable = true) // Adicione esta linha
    private String diasDeTrabalho;

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	public DayOfWeek getDiaDaSemana() {
		return diaDaSemana;
	}

	public void setDiaDaSemana(DayOfWeek diaDaSemana) {
		this.diaDaSemana = diaDaSemana;
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

	public Barbeiro getBarbeiro() {
		return barbeiro;
	}

	public void setBarbeiro(Barbeiro barbeiro) {
		this.barbeiro = barbeiro;
	}

	@Override
	public int hashCode() {
		return Objects.hash(barbeiro, diaDaSemana, horaFim, horaFimAlmoco, horaInicio, horaInicioAlmoco, id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HorariosTrabalho other = (HorariosTrabalho) obj;
		return Objects.equals(barbeiro, other.barbeiro) && diaDaSemana == other.diaDaSemana
				&& Objects.equals(horaFim, other.horaFim) && Objects.equals(horaFimAlmoco, other.horaFimAlmoco)
				&& Objects.equals(horaInicio, other.horaInicio)
				&& Objects.equals(horaInicioAlmoco, other.horaInicioAlmoco) && Objects.equals(id, other.id);
	}
}

