package br.com.pupposoft.poc.cleanarch.conceitual.core.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import br.com.pupposoft.poc.cleanarch.conceitual.core.exception.UsuarioMenorIdadeException;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
public class Motorista {
	private Long id;
	private String nome;
	private String cpf;
	private LocalDate dataNascimento;
	private List<Automovel> automoveis;
	private List<Infracao> infracoes;
	private CalculadoraPrecoMulta calculadora;
	private LocalDateTime dataCriacao;
	
	public Motorista(Long id, String nome, String cpf, LocalDate dataNascimento, List<Automovel> automoveis) {
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.dataNascimento = dataNascimento;
		this.automoveis = automoveis;
		
		dataCriacao = LocalDateTime.now();
	}
	
	public Motorista(Long id, String nome, String cpf, LocalDate dataNascimento) {
		this(id, nome, cpf, dataNascimento, null);
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
		return calculadora.calcular(infracoes);
	}
	
	public void atualizarInfracoes(List<Infracao> infracoes) {
		this.infracoes = infracoes;
	}

	public void atualizarCalculadora(CalculadoraPrecoMulta calculadora) {
		this.calculadora = calculadora;
	}
	
	public boolean possuiInfracaoGrave() {
		return infracoes.stream().anyMatch(Infracao::getGrave);
	}
	
	protected void adicionarAutomovel(Automovel novoAutomovel) {
		if(isMenorIdade()) {
			log.warn("Usuário menor de idade. idade={}", getIdade());
			throw new UsuarioMenorIdadeException();
		}
		
		automoveis.add(novoAutomovel);
	}
	
	public void alterarStatus(String status) {
		//Muda o stado do objeto.
	}
	
}
