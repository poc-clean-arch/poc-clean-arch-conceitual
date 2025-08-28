package br.com.pupposoft.poc.cleanarch.conceitual.core.mapper;

import br.com.pupposoft.poc.cleanarch.conceitual.core.domain.Motorista;
import br.com.pupposoft.poc.cleanarch.conceitual.core.dto.CriarUsuarioInputDto;

public class MotoristaMapper {

	public Motorista map(CriarUsuarioInputDto dto) {

		return new Motorista(dto.getId(), dto.getNome(), dto.getCpf(), dto.getDataNascimento());
	}

}
