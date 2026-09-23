package br.com.pupposoft.poc.cleanarch.conceitual.core.usecase;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;

import br.com.pupposoft.poc.cleanarch.conceitual.core.domain.CalculadoraInfracaoGrave;
import br.com.pupposoft.poc.cleanarch.conceitual.core.domain.CalculadoraInfracaoPadrao;
import br.com.pupposoft.poc.cleanarch.conceitual.core.domain.Infracao;
import br.com.pupposoft.poc.cleanarch.conceitual.core.domain.Motorista;

class ObterCalculadoraMultaUsecaseTest {

	private final ObterCalculadoraMultaUsecase usecase = new ObterCalculadoraMultaUsecase();

	@Test
	void deveRetornarCalculadoraPadraoQuandoNaoHaInfracaoGrave() {
		var motorista = motoristaComInfracoes(infracao(false));

		assertInstanceOf(CalculadoraInfracaoPadrao.class, usecase.obter(motorista));
	}

	@Test
	void deveRetornarCalculadoraDeInfracaoGraveQuandoHaInfracaoGrave() {
		var motorista = motoristaComInfracoes(infracao(true));

		assertInstanceOf(CalculadoraInfracaoGrave.class, usecase.obter(motorista));
	}

	private Motorista motoristaComInfracoes(Infracao... infracoes) {
		var motorista = new Motorista(1L, "Maria", "12345678900", LocalDate.of(1990, 1, 1), List.of());
		motorista.atualizarInfracoes(List.of(infracoes));
		return motorista;
	}

	private Infracao infracao(boolean grave) {
		return new Infracao(1L, LocalDateTime.now(), "Infração", grave, BigDecimal.TEN);
	}
}
