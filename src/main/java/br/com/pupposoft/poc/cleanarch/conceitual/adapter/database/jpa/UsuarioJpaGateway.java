package br.com.pupposoft.poc.cleanarch.conceitual.adapter.database.jpa;

import java.util.Optional;

import org.springframework.stereotype.Component;

import br.com.pupposoft.poc.cleanarch.conceitual.adapter.database.jpa.entity.MotoristaEntity;
import br.com.pupposoft.poc.cleanarch.conceitual.adapter.database.jpa.repository.MotoristaRepository;
import br.com.pupposoft.poc.cleanarch.conceitual.adapter.database.mapper.MotoristaMapper;
import br.com.pupposoft.poc.cleanarch.conceitual.core.domain.Motorista;
import br.com.pupposoft.poc.cleanarch.conceitual.core.exception.ErroAoAcessarRepositorioException;
import br.com.pupposoft.poc.cleanarch.conceitual.core.gateway.MotoristaGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class UsuarioJpaGateway implements MotoristaGateway {

	private final MotoristaRepository motoristaRepository;
	
	private final MotoristaMapper mapper;

	@Override
	public Optional<Motorista> obterPorCpf(String cpf) {
		try {

			Optional<MotoristaEntity> motoristaEntityOp = motoristaRepository.findByCpf(cpf);

			if(motoristaEntityOp.isEmpty()) {
				log.info("Usuário não encontrado: cpf={}", cpf);
				return Optional.empty();
			}

			var motoristaEntity = motoristaEntityOp.get();

			var motorista = mapper.map(motoristaEntity);

			return Optional.of(motorista);


		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new ErroAoAcessarRepositorioException();
		}

	}

	@Override
	public Long criar(Motorista motorista) {
		try {

			MotoristaEntity motoristaEntity = mapper.map(motorista);

			return motoristaRepository.save(motoristaEntity).getId();

		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new ErroAoAcessarRepositorioException();
		}
	}
}
