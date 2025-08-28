package br.com.pupposoft.poc.cleanarch.conceitual.core.domain;

import java.math.BigDecimal;
import java.util.List;

public abstract class CalculadoraPrecoMulta {

	abstract BigDecimal calcular(List<Infracao> infracoes);

	protected BigDecimal obterValorTotal(List<Infracao> infracoes) {
		return infracoes.stream().map(Infracao::getValor).reduce(BigDecimal.ZERO, BigDecimal::add);
	}
	
}
