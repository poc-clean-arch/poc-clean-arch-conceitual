package br.com.pupposoft.poc.cleanarch.conceitual.core.usecase;

import br.com.pupposoft.poc.cleanarch.conceitual.core.dto.CriarMotoristaInputDto;
import br.com.pupposoft.poc.cleanarch.conceitual.core.dto.CriarMotoristaOutputDto;

public interface CriarUsuarioUsecase {

	CriarMotoristaOutputDto criar(CriarMotoristaInputDto inputDto);

}
