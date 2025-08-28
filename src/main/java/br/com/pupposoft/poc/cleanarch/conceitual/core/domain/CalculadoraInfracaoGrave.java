package br.com.pupposoft.poc.cleanarch.conceitual.core.domain;

import java.math.BigDecimal;
import java.util.List;

public class CalculadoraInfracaoGrave extends CalculadoraPrecoMulta {

	private static final BigDecimal ADICIONAL_MULTA = new BigDecimal("50.0");
	
	@Override
	public BigDecimal calcular(List<Infracao> infracoes) {
		
		var valorTotalInfracoes = obterValorTotal(infracoes);
		
		return valorTotalInfracoes.add(ADICIONAL_MULTA);
		
	}

}
