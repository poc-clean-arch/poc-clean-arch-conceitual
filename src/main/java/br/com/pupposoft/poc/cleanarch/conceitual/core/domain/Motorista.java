package br.com.pupposoft.poc.cleanarch.conceitual.core.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import br.com.pupposoft.poc.cleanarch.conceitual.core.exception.UsuarioMenorIdadeException;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Builder(access = AccessLevel.PROTECTED)
public class Motorista {
	private Long id;
	private String nome;
	private String cpf;
	private LocalDate dataNascimento;
	private List<Automovel> automoveis;
	private List<Infracao> infracoes;
	private CalculadoraPrecoMulta calculadora;
	private LocalDateTime dataCriacao;
	private LocalDateTime dataInativacao;
	private Boolean ativo;

	public void inativar(){
		ativo = false;
		dataInativacao = LocalDateTime.now();
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
		//TODO: Muda o stado do objeto.
	}

	public boolean totalMultasExcedido(){
		return getTotalMultas().compareTo(new BigDecimal("15000.0")) >= 0;
	}
	
}
