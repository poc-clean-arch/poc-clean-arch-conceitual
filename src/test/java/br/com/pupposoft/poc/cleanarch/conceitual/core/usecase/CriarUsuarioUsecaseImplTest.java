package br.com.pupposoft.poc.cleanarch.conceitual.core.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.pupposoft.poc.cleanarch.conceitual.core.domain.Automovel;
import br.com.pupposoft.poc.cleanarch.conceitual.core.domain.CalculadoraInfracaoPadrao;
import br.com.pupposoft.poc.cleanarch.conceitual.core.domain.Infracao;
import br.com.pupposoft.poc.cleanarch.conceitual.core.domain.Motorista;
import br.com.pupposoft.poc.cleanarch.conceitual.core.exception.UsuarioComAutomovelAntigoException;
import br.com.pupposoft.poc.cleanarch.conceitual.core.exception.UsuarioExistenteException;
import br.com.pupposoft.poc.cleanarch.conceitual.core.exception.UsuarioMenorIdadeException;
import br.com.pupposoft.poc.cleanarch.conceitual.core.exception.UsuarioSemAutomovelCadastradoException;
import br.com.pupposoft.poc.cleanarch.conceitual.core.gateway.InfracaoGateway;
import br.com.pupposoft.poc.cleanarch.conceitual.core.gateway.MotoristaGateway;
import br.com.pupposoft.poc.cleanarch.conceitual.core.gateway.NotificacaoGateway;

@ExtendWith(MockitoExtension.class)
class CriarUsuarioUsecaseImplTest {

	@Mock
	private MotoristaGateway motoristaGateway;
	@Mock
	private InfracaoGateway infracaoGateway;
	@Mock
	private ObterCalculadoraMultaUsecase obterCalculadoraMultaUsecase;
	@Mock
	private NotificacaoGateway notificacaoGateway;

	private CriarUsuarioUsecaseImpl usecase;

	@BeforeEach
	void setUp() {
		usecase = new CriarUsuarioUsecaseImpl(
				motoristaGateway, infracaoGateway, obterCalculadoraMultaUsecase, notificacaoGateway);
		when(infracaoGateway.obterPorCpf(any())).thenReturn(List.of());
		when(obterCalculadoraMultaUsecase.obter(any(Motorista.class))).thenReturn(new CalculadoraInfracaoPadrao());
		when(motoristaGateway.obterPorCpf(any())).thenReturn(Optional.empty());
	}

	@Test
	void deveEnriquecerValidarEPersistirMotoristaValido() {
		var motorista = motoristaAdultoComAutomovel(LocalDate.now().minusYears(1));
		var infracoes = List.of(infracao(false, "100.00"));
		when(infracaoGateway.obterPorCpf(motorista.getCpf())).thenReturn(infracoes);
		when(motoristaGateway.criar(motorista)).thenReturn(42L);

		var id = usecase.criar(motorista);

		var motoristaPersistido = ArgumentCaptor.forClass(Motorista.class);
		verify(motoristaGateway).criar(motoristaPersistido.capture());
		assertEquals(42L, id);
		assertSame(motorista, motoristaPersistido.getValue());
		assertEquals(infracoes, motorista.getInfracoes());
		verify(obterCalculadoraMultaUsecase).obter(motorista);
		verify(notificacaoGateway, never()).notificarRisco(any());
	}

	@Test
	void deveRejeitarQuandoCpfJaExiste() {
		var motorista = motoristaAdultoComAutomovel(LocalDate.now().minusYears(1));
		when(motoristaGateway.obterPorCpf(motorista.getCpf())).thenReturn(Optional.of(motorista));

		assertThrows(UsuarioExistenteException.class, () -> usecase.criar(motorista));

		verify(motoristaGateway, never()).criar(any());
		verify(notificacaoGateway, never()).notificarRisco(any());
	}

	@Test
	void deveRejeitarMotoristaMenorDeIdade() {
		var motorista = new Motorista(1L, "Maria", "12345678900", LocalDate.now().minusYears(17),
				List.of(automovel(LocalDate.now().minusYears(1))));

		assertThrows(UsuarioMenorIdadeException.class, () -> usecase.criar(motorista));

		verify(motoristaGateway, never()).criar(any());
	}

	@Test
	void deveRejeitarMotoristaSemAutomovel() {
		var motorista = new Motorista(1L, "Maria", "12345678900", LocalDate.of(1990, 1, 1), List.of());

		assertThrows(UsuarioSemAutomovelCadastradoException.class, () -> usecase.criar(motorista));

		verify(motoristaGateway, never()).criar(any());
	}

	@Test
	void deveRejeitarMotoristaComAutomovelAntigo() {
		var motorista = motoristaAdultoComAutomovel(LocalDate.now().minusYears(3).minusDays(1));

		assertThrows(UsuarioComAutomovelAntigoException.class, () -> usecase.criar(motorista));

		verify(motoristaGateway, never()).criar(any());
	}

	@Test
	void deveNotificarERespeitarCriacaoQuandoMultasAtingemLimiteDeRisco() {
		var motorista = motoristaAdultoComAutomovel(LocalDate.now().minusYears(1));
		when(infracaoGateway.obterPorCpf(motorista.getCpf())).thenReturn(List.of(infracao(false, "15000.00")));
		when(motoristaGateway.criar(motorista)).thenReturn(42L);

		var id = usecase.criar(motorista);

		assertEquals(42L, id);
		verify(notificacaoGateway).notificarRisco(motorista);
		verify(motoristaGateway).criar(motorista);
	}

	private Motorista motoristaAdultoComAutomovel(LocalDate dataModelo) {
		return new Motorista(1L, "Maria", "12345678900", LocalDate.of(1990, 1, 1), List.of(automovel(dataModelo)));
	}

	private Automovel automovel(LocalDate dataModelo) {
		return new Automovel(1L, "Modelo", dataModelo, null);
	}

	private Infracao infracao(boolean grave, String valor) {
		return new Infracao(1L, LocalDateTime.now(), "Infração", grave, new BigDecimal(valor));
	}
}
