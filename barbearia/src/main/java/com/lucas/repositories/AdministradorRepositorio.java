package com.lucas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lucas.models.Adminstrador;

@Repository
public interface AdministradorRepositorio extends JpaRepository<Adminstrador, Long> {
	Adminstrador findByEmail(String email);
}