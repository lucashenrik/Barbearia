package com.lucas.models;

public enum EnumRoles {
	ADMIN("admin"),
	BARBEIRO("barbeiro"),
	CLIENTE("cliente");
	
	private String role;
	
	EnumRoles(String role){
		this.role = role;
	}
	
	public String getRole() {
		return role;
	}
}
