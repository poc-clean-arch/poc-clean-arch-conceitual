package br.com.pupposoft.poc.cleanarch.conceitual.core.usecase;

import java.util.Optional;

import br.com.pupposoft.poc.cleanarch.conceitual.core.domain.Motorista;
import br.com.pupposoft.poc.cleanarch.conceitual.core.exception.UsuarioComAutomovelAntigoException;
import br.com.pupposoft.poc.cleanarch.conceitual.core.exception.UsuarioExistenteException;
import br.com.pupposoft.poc.cleanarch.conceitual.core.exception.UsuarioMenorIdadeException;
import br.com.pupposoft.poc.cleanarch.conceitual.core.exception.UsuarioSemAutomovelCadastradoException;
import br.com.pupposoft.poc.cleanarch.conceitual.core.gateway.MotoristaGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class CriarUsuarioUsecaseImpl implements CriarUsuarioUsecase {

	private final MotoristaGateway motoritaGateway;

	@Override
	public Long criar(Motorista motorista) {

		Optional<Motorista> motoristaOp = motoritaGateway.obterPorCpf(motorista.getCpf());
		
		aplicarRegras(motorista, motoristaOp);
		
		return motoritaGateway.criar(motorista);
	}

	private void aplicarRegras(Motorista motorista, Optional<Motorista> motoristaOp) {
		if(motoristaOp.isPresent()) {
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
	}
}
