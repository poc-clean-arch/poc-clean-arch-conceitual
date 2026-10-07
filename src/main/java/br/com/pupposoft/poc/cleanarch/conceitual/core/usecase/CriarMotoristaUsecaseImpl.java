package br.com.pupposoft.poc.cleanarch.conceitual.core.usecase;

import br.com.pupposoft.poc.cleanarch.conceitual.core.domain.Motorista;
import br.com.pupposoft.poc.cleanarch.conceitual.core.dto.CriarMotoristaInputDto;
import br.com.pupposoft.poc.cleanarch.conceitual.core.dto.CriarMotoristaOutputDto;
import br.com.pupposoft.poc.cleanarch.conceitual.core.exception.UsuarioComAutomovelAntigoException;
import br.com.pupposoft.poc.cleanarch.conceitual.core.exception.UsuarioExistenteException;
import br.com.pupposoft.poc.cleanarch.conceitual.core.exception.UsuarioMenorIdadeException;
import br.com.pupposoft.poc.cleanarch.conceitual.core.exception.UsuarioSemAutomovelCadastradoException;
import br.com.pupposoft.poc.cleanarch.conceitual.core.factory.MotoristaFactory;
import br.com.pupposoft.poc.cleanarch.conceitual.core.gateway.InfracaoGateway;
import br.com.pupposoft.poc.cleanarch.conceitual.core.gateway.MotoristaGateway;
import br.com.pupposoft.poc.cleanarch.conceitual.core.gateway.NotificacaoGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class CriarMotoristaUsecaseImpl implements CriarUsuarioUsecase {
	private final MotoristaGateway motoritaGateway;
	private final InfracaoGateway infracaoGateway;
	private final ObterCalculadoraMultaUsecase obterCalculadoraMultaUsecase;
	private final NotificacaoGateway notificacaoGateway;
	private final MotoristaFactory motoristaFactory;

	@Override
	public CriarMotoristaOutputDto criar(CriarMotoristaInputDto inputDto) {

		var novoMotorista = motoristaFactory.criar(inputDto);

		obterInfracoes(novoMotorista);

		obterCalculadoraMulta(novoMotorista);
		
		aplicarRegras(novoMotorista);

		var motoristaId = motoritaGateway.criar(novoMotorista);

		return CriarMotoristaOutputDto.builder()
				.id(motoristaId)
				.build();
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

		//TODO: cada regra pode ser um rule (Strategy)

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
		
		if(motorista.totalMultasExcedido()) {
			notificacaoGateway.notificarRisco(motorista);
		}
	}
}
