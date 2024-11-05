package br.com.pupposoft.poc.cleanarch.conceitual.adapter.database.jpa;

import java.util.List;
import java.util.Optional;

import br.com.pupposoft.poc.cleanarch.conceitual.adapter.database.jpa.entity.UsuarioEntity;
import br.com.pupposoft.poc.cleanarch.conceitual.adapter.database.jpa.repository.UsuarioRepository;
import br.com.pupposoft.poc.cleanarch.conceitual.core.domain.Automovel;
import br.com.pupposoft.poc.cleanarch.conceitual.core.domain.Usuario;
import br.com.pupposoft.poc.cleanarch.conceitual.core.exception.ErroAoAcessarRepositorioException;
import br.com.pupposoft.poc.cleanarch.conceitual.core.gateway.UsuarioGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class UsuarioJpaGateway implements UsuarioGateway {

	private final UsuarioRepository usuarioRepository;
	
	@Override
	public Optional<Usuario> obterPorCpf(String cpf) {
		try {
			
			Optional<UsuarioEntity> usuarioEntityOp = usuarioRepository.findByCpf(cpf);
			
			if(usuarioEntityOp.isEmpty()) {
				log.info("Usuário não encontrado: cpf={}", cpf);
				return Optional.empty();
			}
			
			UsuarioEntity usuarioEntity = usuarioEntityOp.get();
			
			Usuario usuario = mapToDomain(usuarioEntity);
			
			return Optional.of(usuario);
			
			
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new ErroAoAcessarRepositorioException();
		}
		
	}

	@Override
	public Long criar(Usuario usuario) {
		try {
			
			UsuarioEntity usuarioEntity = mapToEntity(usuario);
			
			return usuarioRepository.save(usuarioEntity).getId();
			
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new ErroAoAcessarRepositorioException();
		}
	}

	private Usuario mapToDomain(UsuarioEntity usuarioEntity) {
		List<Automovel> automoveis = usuarioEntity.getAutomoveis().stream().map(ae -> {
			return new Automovel(
					ae.getId(),
					ae.getModelo(),
					ae.getDataModelo(),
					null);
		}).toList();
		
		return new Usuario(
						usuarioEntity.getId(), 
						usuarioEntity.getCpf(), 
						usuarioEntity.getNome(), 
						usuarioEntity.getDataNascimento(), 
						automoveis);
	}
	
	private UsuarioEntity mapToEntity(Usuario usuario) {
		return UsuarioEntity.builder()
				//FIXME: implementar
				
				.build();
	}
	

}
