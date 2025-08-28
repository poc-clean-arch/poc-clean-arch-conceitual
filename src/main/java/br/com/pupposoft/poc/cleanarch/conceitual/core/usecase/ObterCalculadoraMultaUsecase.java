package br.com.pupposoft.poc.cleanarch.conceitual.core.usecase;

import org.springframework.stereotype.Service;

import br.com.pupposoft.poc.cleanarch.conceitual.core.domain.CalculadoraInfracaoGrave;
import br.com.pupposoft.poc.cleanarch.conceitual.core.domain.CalculadoraInfracaoPadrao;
import br.com.pupposoft.poc.cleanarch.conceitual.core.domain.CalculadoraPrecoMulta;
import br.com.pupposoft.poc.cleanarch.conceitual.core.domain.Motorista;

@Service
public class ObterCalculadoraMultaUsecase {

	public CalculadoraPrecoMulta obter(Motorista motorista) {

		if(motorista.possuiInfracaoGrave()) {
			return new CalculadoraInfracaoGrave();
		}
		
		return new CalculadoraInfracaoPadrao();
	}

}
