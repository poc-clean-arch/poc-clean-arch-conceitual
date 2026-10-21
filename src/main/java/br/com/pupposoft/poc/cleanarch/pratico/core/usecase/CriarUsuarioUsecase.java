package br.com.pupposoft.poc.cleanarch.pratico.core.usecase;

import br.com.pupposoft.poc.cleanarch.pratico.core.dto.CriarMotoristaInputDto;
import br.com.pupposoft.poc.cleanarch.pratico.core.dto.CriarMotoristaOutputDto;

public interface CriarUsuarioUsecase {

	CriarMotoristaOutputDto criar(CriarMotoristaInputDto inputDto);

}
