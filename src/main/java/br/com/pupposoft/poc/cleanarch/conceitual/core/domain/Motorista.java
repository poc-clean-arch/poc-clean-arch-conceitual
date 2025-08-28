package br.com.pupposoft.poc.cleanarch.conceitual.core.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Motorista {
	private Long id;
	private String nome;
	private String cpf;
	private LocalDate dataNascimento;
	private List<Automovel> automoveis;
	private List<Infracao> infracoes;
	private CalculadoraPrecoMulta calculadoraPreco;
	
	public Motorista(Long id, String nome, String cpf, LocalDate dataNascimento, List<Automovel> automoveis) {
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.dataNascimento = dataNascimento;
		this.automoveis = automoveis;
	}	
	
	public Long getIdade() {
		return dataNascimento.until(LocalDate.now(), ChronoUnit.YEARS);
	}
	
	public boolean isMenorIdade() {
		return getIdade() < 18;
	}
	
	public boolean semAutomovel() {
		return automoveis.isEmpty();
	}
	
	public boolean temCarroAntigo() {
		return automoveis.stream().anyMatch(Automovel::isAntigo);
	}
	
	public BigDecimal getTotalMultas() {
		return calculadoraPreco.calcular(infracoes);
	}
}
