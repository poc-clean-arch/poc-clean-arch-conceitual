package br.com.pupposoft.poc.cleanarch.conceitual.core.domain;

import java.math.BigDecimal;
import java.util.List;

public interface CalculadoraPrecoMulta {

	BigDecimal calcular(List<Infracao> infracoes);

}
