package com.lucas.models.dtos;

import com.lucas.models.EnumRoles;

public record RegistrarDTO(String nome, String email, String telefone, String senha, EnumRoles role) {

	
}
