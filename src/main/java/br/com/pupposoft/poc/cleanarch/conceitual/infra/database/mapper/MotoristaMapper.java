package br.com.pupposoft.poc.cleanarch.conceitual.infra.database.mapper;

import br.com.pupposoft.poc.cleanarch.conceitual.core.factory.MotoristaFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import br.com.pupposoft.poc.cleanarch.conceitual.core.domain.Automovel;
import br.com.pupposoft.poc.cleanarch.conceitual.core.domain.Motorista;
import br.com.pupposoft.poc.cleanarch.conceitual.infra.database.jpa.entity.AutomovelEntity;
import br.com.pupposoft.poc.cleanarch.conceitual.infra.database.jpa.entity.MotoristaEntity;

@Component
@RequiredArgsConstructor
public class MotoristaMapper {

	private final MotoristaFactory motoristaFactory;

	public Motorista map(MotoristaEntity motoristaEntity) {
		//FIXME: Criar factory de automovel
		var automoveis = motoristaEntity
		.getAutomoveis()
		.stream()
		.map(a -> new Automovel(
					a.getId(),
					a.getModelo(),
					a.getDataModelo(),
					null))
		.toList();
		
		return motoristaFactory.criar(motoristaEntity.getId(), motoristaEntity.getNome(), motoristaEntity.getCpf(), motoristaEntity.getDataNascimento(), automoveis);
	}

	public MotoristaEntity map(Motorista motorista) {
		
		var automoveisEntity = motorista.getAutomoveis()
		.stream()
		.map(a -> new AutomovelEntity(
				a.getId(),
				a.getModelo(),
				a.getDataModelo(),
				null)).toList();
		
		var motoristaEntity = new MotoristaEntity(
				motorista.getId(), 
				motorista.getCpf(), 
				motorista.getNome(), 
				motorista.getDataNascimento(), 
				motorista.getTotalMultas().doubleValue(), 
				automoveisEntity);
		
		motoristaEntity.getAutomoveis().forEach(a -> a.setMotorista(motoristaEntity));
		
		return motoristaEntity;
	}

}
