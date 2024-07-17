package com.lucas.models.dtos;

public enum DuracaoEnum {
	PT10M("10 minutes"),
	PT15M("15 minutes"),
	PT20M("20 minutes"),
	PT30M("30 minutes"),
	PT40M("40 minutes"),
	PT1H("1 hour"),
	PT1H30M("1 hour and 30 minutes"),
	PT2H("2 hours"),
	PT2H30M("2 hours and 30 minute"),
	PT3H("3 hours"),
	PT3H30M("3 hours and 30 minute"),
	PT4H("4 hours"),
	PT4H30M("4 hours and 30 minute");


	private final String descricao;

	DuracaoEnum(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
	     return descricao;
	}
}

