package com.lucas.models;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Table(name = "tb_barbeiro")
@Entity(name = "tb_barbeiro")
/*@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")*/
//cria getters and setters e construtores atutomatico com o lombok

public class Barbeiro implements UserDetails {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String nome;
	
	@Column(unique = true)
	private String email;
	
	@Column(nullable = false, length = 11)
	private String telefone;
	
	@Column(nullable = false)
	private String senha;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private EnumRoles role;
	
	@OneToMany(mappedBy = "barbeiro")
	@JsonIgnore
	private List<Servico> servicos;
	
	@OneToMany(mappedBy = "barbeiro")
	@JsonIgnore
	private List<Atendimentos> atendimentos;
	
    @OneToMany(mappedBy = "barbeiro", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<HorariosTrabalho> horariosTrabalho = new HashSet<>();
    
    @ManyToOne
    @JoinColumn(name = "administrador_id")
    @JsonIgnore
    private Adminstrador administrador;
    
    @ManyToOne
    @JoinColumn(name = "barbearia_id")
    @JsonBackReference
    private Barbearia barbearia;

    public Barbeiro() {}

	public Barbeiro(String nome, String email, String telefone, String senha, EnumRoles role,
			Adminstrador administrador, Barbearia barbearia) {
		this.nome = nome;
		this.email = email;
		this.telefone = telefone;
		this.senha = senha;
		this.role = role;
		this.administrador = administrador;
		this.barbearia = barbearia;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public EnumRoles getRole() {
		return role;
	}

	public void setRole(EnumRoles role) {
		this.role = role;
	}

	public List<Servico> getServicos() {
		return servicos;
	}

	public void setServicos(List<Servico> servicos) {
		this.servicos = servicos;
	}

	public List<Atendimentos> getAtendimentos() {
		return atendimentos;
	}

	public void setAtendimentos(List<Atendimentos> atendimentos) {
		this.atendimentos = atendimentos;
	}

	public Set<HorariosTrabalho> getHorariosTrabalho() {
		return horariosTrabalho;
	}

	public void setHorariosTrabalho(Set<HorariosTrabalho> horariosTrabalho) {
		this.horariosTrabalho = horariosTrabalho;
	}

	public Adminstrador getAdministrador() {
		return administrador;
	}

	public void setAdministrador(Adminstrador administrador) {
		this.administrador = administrador;
	}

	public Barbearia getBarbearia() {
		return barbearia;
	}

	public void setBarbearia(Barbearia barbearia) {
		this.barbearia = barbearia;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		return Objects.hash(administrador, atendimentos, barbearia, email, horariosTrabalho, id, nome, role, senha,
				servicos, telefone);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Barbeiro other = (Barbeiro) obj;
		return Objects.equals(administrador, other.administrador) && Objects.equals(atendimentos, other.atendimentos)
				&& Objects.equals(barbearia, other.barbearia) && Objects.equals(email, other.email)
				&& Objects.equals(horariosTrabalho, other.horariosTrabalho) && Objects.equals(id, other.id)
				&& Objects.equals(nome, other.nome) && role == other.role && Objects.equals(senha, other.senha)
				&& Objects.equals(servicos, other.servicos) && Objects.equals(telefone, other.telefone);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		if (this.role == EnumRoles.BARBEIRO) return List.of(new SimpleGrantedAuthority("BARBEIRO"), 
				new SimpleGrantedAuthority("CLIENTE"));
		else return List.of(new SimpleGrantedAuthority("CLIENTE"));
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
}