package br.com.pupposoft.poc.cleanarch.pratico.core.usecase;

import br.com.pupposoft.poc.cleanarch.pratico.core.domain.CalculadoraInfracaoGrave;
import br.com.pupposoft.poc.cleanarch.pratico.core.domain.CalculadoraInfracaoPadrao;
import br.com.pupposoft.poc.cleanarch.pratico.core.domain.CalculadoraPrecoMulta;
import br.com.pupposoft.poc.cleanarch.pratico.core.domain.Motorista;

public class ObterCalculadoraMultaUsecase {

	public CalculadoraPrecoMulta obter(Motorista motorista) {

		if(motorista.possuiInfracaoGrave()) {
			return new CalculadoraInfracaoGrave();
		}
		
		return new CalculadoraInfracaoPadrao();
	}

}
