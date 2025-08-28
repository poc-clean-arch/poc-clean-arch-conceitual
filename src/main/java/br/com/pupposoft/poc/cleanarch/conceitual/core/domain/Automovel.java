package br.com.pupposoft.poc.cleanarch.conceitual.core.domain;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Automovel {
	private Long id;
	private String modelo;
	private LocalDate dataModelo;
	private Motorista motorista;
	
	public boolean isAntigo() {
		return dataModelo.isBefore(LocalDate.now().minusYears(3));
	}
}
