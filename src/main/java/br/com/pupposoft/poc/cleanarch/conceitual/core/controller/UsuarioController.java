package br.com.pupposoft.poc.cleanarch.conceitual.core.controller;

import br.com.pupposoft.poc.cleanarch.conceitual.core.dto.CriarUsuarioInputDto;
import br.com.pupposoft.poc.cleanarch.conceitual.core.mapper.MotoristaMapper;
import br.com.pupposoft.poc.cleanarch.conceitual.core.usecase.CriarUsuarioUsecase;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UsuarioController {

	private final CriarUsuarioUsecase criarUsuarioUsecase;
	
	private final MotoristaMapper motoristaMapper;
	
	public Long criar(CriarUsuarioInputDto criarUsuarioInputDto) {
		
		var motorista = motoristaMapper.map(criarUsuarioInputDto);
		
		return criarUsuarioUsecase.criar(motorista);
	}
}
