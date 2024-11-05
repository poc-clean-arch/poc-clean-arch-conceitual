package br.com.pupposoft.poc.cleanarch.conceitual.core.usecase;

import java.util.Optional;

import br.com.pupposoft.poc.cleanarch.conceitual.core.domain.Usuario;
import br.com.pupposoft.poc.cleanarch.conceitual.core.exception.UsuarioComAutomovelAntigoException;
import br.com.pupposoft.poc.cleanarch.conceitual.core.exception.UsuarioExistenteException;
import br.com.pupposoft.poc.cleanarch.conceitual.core.exception.UsuarioMenorIdadeException;
import br.com.pupposoft.poc.cleanarch.conceitual.core.exception.UsuarioSemAutomovelCadastradoException;
import br.com.pupposoft.poc.cleanarch.conceitual.core.gateway.UsuarioGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class CriarUsuarioUsecaseImpl implements CriarUsuarioUsecase {

	private final UsuarioGateway usuarioGateway;

	@Override
	public Long criar(Usuario usuario) {

		Optional<Usuario> usuarioOp = usuarioGateway.obterPorCpf(usuario.getCpf());
		if(usuarioOp.isPresent()) {
			log.warn("Usuário ja existe com cpf informado. {}", usuario.getCpf());
			throw new UsuarioExistenteException();
		}
		
		if(usuario.isMenorIdade()) {
			log.warn("Usuário menor de idade. idade={}", usuario.getIdade());
			throw new UsuarioMenorIdadeException();
		}
		
		if(usuario.semAutomovel()) {
			log.warn("Usuário sem automovel");
			throw new UsuarioSemAutomovelCadastradoException();
		}
		
		if(usuario.temCarroAntigo()) {
			log.warn("Usuário possui automoveis antigos");
			throw new UsuarioComAutomovelAntigoException();
		}
		
		return usuarioGateway.criar(usuario);
	}
}
