package br.com.pupposoft.poc.cleanarch.conceitual.core.domain;

import java.math.BigDecimal;
import java.util.List;

public class CalculadoraInfracaoPadrao extends CalculadoraPrecoMulta {

	@Override
	BigDecimal calcular(List<Infracao> infracoes) {
		return obterValorTotal(infracoes);
	}

}
