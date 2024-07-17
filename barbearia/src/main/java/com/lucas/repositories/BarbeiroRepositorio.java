package com.lucas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lucas.models.Barbeiro;

@Repository
public interface BarbeiroRepositorio extends JpaRepository<Barbeiro, Long>{

}
