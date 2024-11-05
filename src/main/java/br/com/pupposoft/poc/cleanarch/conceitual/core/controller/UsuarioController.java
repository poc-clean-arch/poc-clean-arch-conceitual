package br.com.pupposoft.poc.cleanarch.conceitual.core.controller;

import br.com.pupposoft.poc.cleanarch.conceitual.core.domain.Usuario;
import br.com.pupposoft.poc.cleanarch.conceitual.core.dto.CriarUsuarioInputDto;
import br.com.pupposoft.poc.cleanarch.conceitual.core.usecase.CriarUsuarioUsecase;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UsuarioController {

	private CriarUsuarioUsecase criarUsuarioUsecase;
	
	public Long criar(CriarUsuarioInputDto criarUsuarioInputDto) {
		return criarUsuarioUsecase.criar(mapToDomain(criarUsuarioInputDto));
	}

	private Usuario mapToDomain(CriarUsuarioInputDto criarUsuarioInputDto) {
		return new Usuario(
				criarUsuarioInputDto.getId(),
				criarUsuarioInputDto.getNome(),
				criarUsuarioInputDto.getCpf(),
				criarUsuarioInputDto.getDataNascimento(),
				null//FIXME: implementar lista de automoveis NOSONAR
				);
	}
}
