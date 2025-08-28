package br.com.pupposoft.poc.cleanarch.conceitual.core.usecase;

import java.math.BigDecimal;

import br.com.pupposoft.poc.cleanarch.conceitual.core.domain.Motorista;
import br.com.pupposoft.poc.cleanarch.conceitual.core.exception.UsuarioComAutomovelAntigoException;
import br.com.pupposoft.poc.cleanarch.conceitual.core.exception.UsuarioExistenteException;
import br.com.pupposoft.poc.cleanarch.conceitual.core.exception.UsuarioMenorIdadeException;
import br.com.pupposoft.poc.cleanarch.conceitual.core.exception.UsuarioSemAutomovelCadastradoException;
import br.com.pupposoft.poc.cleanarch.conceitual.core.gateway.InfracaoGateway;
import br.com.pupposoft.poc.cleanarch.conceitual.core.gateway.MotoristaGateway;
import br.com.pupposoft.poc.cleanarch.conceitual.core.gateway.NotificacaoGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class CriarUsuarioUsecaseImpl implements CriarUsuarioUsecase {

	private final MotoristaGateway motoritaGateway;
	private final InfracaoGateway infracaoGateway;
	private final ObterCalculadoraMultaUsecase obterCalculadoraMultaUsecase;
	private final NotificacaoGateway notificacaoGateway;

	@Override
	public Long criar(Motorista novoMotorista) {

		obterCalculadoraMulta(novoMotorista);
		
		obterInfracoes(novoMotorista);
		
		aplicarRegras(novoMotorista);
		
		return motoritaGateway.criar(novoMotorista);
	}

	private void obterInfracoes(Motorista novoMotorista) {
		var infracoes = infracaoGateway.obterPorCpf(novoMotorista.getCpf());
		novoMotorista.atualizarInfracoes(infracoes);
	}

	private void obterCalculadoraMulta(Motorista novoMotorista) {
		var calculadora = obterCalculadoraMultaUsecase.obter(novoMotorista);
		novoMotorista.atualizarCalculadora(calculadora);
	}

	private void aplicarRegras(Motorista motorista) {
		
		var motoristaExistenteOp = motoritaGateway.obterPorCpf(motorista.getCpf());
		if(motoristaExistenteOp.isPresent()) {
			log.warn("Usuário ja existe com cpf informado. {}", motorista.getCpf());
			throw new UsuarioExistenteException();
		}
		
		if(motorista.isMenorIdade()) {
			log.warn("Usuário menor de idade. idade={}", motorista.getIdade());
			throw new UsuarioMenorIdadeException();
		}
		
		if(motorista.semAutomovel()) {
			log.warn("Usuário sem automovel");
			throw new UsuarioSemAutomovelCadastradoException();
		}
		
		if(motorista.temCarroAntigo()) {
			log.warn("Usuário possui automoveis antigos");
			throw new UsuarioComAutomovelAntigoException();
		}
		
		if(motorista.getTotalMultas().compareTo(new BigDecimal("15000.0")) >= 0) {
			notificacaoGateway.notificarRisco(motorista);
		}
	}
}
